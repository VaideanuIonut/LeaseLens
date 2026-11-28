package com.leaselens.backend;

import com.leaselens.backend.entity.*;
import com.leaselens.backend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Component
public class DevDataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DevDataSeeder.class);
    private static final LocalDate REFERENCE_DATE = LocalDate.of(2026, 11, 27);
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2026, 11, 27, 12, 0);

    private static final List<String> PROPERTY_TYPES = List.of(
            "Office", "Industrial / Logistics", "Retail", "Medical");
    private static final List<String> CITIES = List.of(
            "Chicago", "Dallas", "Atlanta", "Boston", "Seattle", "Denver", "Austin", "Phoenix");
    private static final List<String> PROPERTY_NAME_PREFIXES = List.of(
            "Oakridge", "Metro West", "Riverfront", "St. Jude", "Lakeshore", "Cedar Grove",
            "Summit", "Parkway", "Northpoint", "Easton", "Westlake", "Pinecrest",
            "Brookfield", "Canyon", "Grandview", "Meadowlands", "Stonebridge", "Mill Creek",
            "Fairview", "Highland", "Redwood", "Silverstone", "Greenway", "Crescent",
            "Heritage", "Liberty", "Union Square", "Bluewater", "Maplewood", "Crossroads");
    private static final List<String> PROPERTY_NAME_SUFFIXES = List.of(
            "Business Park", "Distribution Center", "Retail Court", "Outpatient Plaza",
            "Commerce Center", "Logistics Campus", "Medical Center", "Office Plaza",
            "Industrial Park", "Shopping Center", "Corporate Plaza", "Technology Park");
    private static final List<String> STREET_NAMES = List.of(
            "Lake", "Market", "Peachtree", "Congress", "Pine", "Broadway", "Cedar",
            "Franklin", "Monroe", "Highland", "Washington", "Park", "Madison", "River");
    private static final List<String> STREET_SUFFIXES = List.of(
            "Street", "Avenue", "Boulevard", "Road", "Drive", "Parkway");

    private static final List<String> COMPANY_PREFIXES = List.of(
            "Apex", "Blue Oak", "Cedarstone", "Northstar", "BrightPath", "Sterling",
            "Evergreen", "Summit", "Pioneer", "Harborview", "Clearwater", "Redwood",
            "Atlas", "Meridian", "Silverline", "Westbridge", "Oakwell", "Crestpoint",
            "Horizon", "Noble", "Lakeside", "Ironwood", "Cornerstone", "Pinnacle",
            "Beacon", "Cobalt", "Fieldstone", "Keystone", "Parkview", "Granite",
            "Bluebird", "Vantage", "Crown", "Aspen", "Urban", "Cypress", "Fairway",
            "Maple", "Canyon", "Liberty", "Bridgewell", "Everwell", "Northgate",
            "Highland", "Stonebrook", "Westfield", "Riverside", "Greenfield",
            "Foxglove", "Redstone");
    private static final List<String> COMPANY_SUFFIXES = List.of(
            "Technology Group", "Clinical Services", "Logistics Partners", "Retail Group",
            "Legal Associates", "Consulting Partners", "Analytics Group", "Supply Company",
            "Manufacturing Group", "Healthcare Services", "Systems Corporation",
            "Advisory Group");
    private static final List<String> FIRST_NAMES = List.of(
            "Jordan", "Taylor", "Morgan", "Casey", "Avery", "Riley", "Cameron", "Jamie",
            "Alex", "Drew", "Parker", "Quinn", "Reese", "Robin", "Skyler", "Sydney",
            "Blake", "Charlie", "Dakota", "Emerson");
    private static final List<String> LAST_NAMES = List.of(
            "Anderson", "Bennett", "Carter", "Davis", "Edwards", "Foster", "Garcia",
            "Hughes", "Jackson", "Kim", "Lewis", "Mitchell", "Nelson", "Parker",
            "Robinson", "Stewart", "Turner", "Walker", "Young", "Brooks");
    private static final List<String> PHONE_AREA_CODES = List.of(
            "312", "214", "404", "617", "206", "303", "512", "602");

    private final PropertyRepository propertyRepository;
    private final PropertyTypeRepository propertyTypeRepository;
    private final UnitRepository unitRepository;
    private final TenantRepository tenantRepository;
    private final LeaseRepository leaseRepository;
    private final LeaseUnitRepository leaseUnitRepository;
    private final RentRollRepository rentRollRepository;
    private final RentArrearsRepository rentArrearsRepository;
    private final LeaseExpiryRepository leaseExpiryRepository;
    private final IndexationDueRepository indexationDueRepository;
    private final RenewalDeadlineRepository renewalDeadlineRepository;
    private final BreakDeadlineRepository breakDeadlineRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DevDataSeeder(
            PropertyRepository propertyRepository,
            PropertyTypeRepository propertyTypeRepository,
            UnitRepository unitRepository,
            TenantRepository tenantRepository,
            LeaseRepository leaseRepository,
            LeaseUnitRepository leaseUnitRepository,
            RentRollRepository rentRollRepository,
            RentArrearsRepository rentArrearsRepository,
            LeaseExpiryRepository leaseExpiryRepository,
            IndexationDueRepository indexationDueRepository,
            RenewalDeadlineRepository renewalDeadlineRepository,
            BreakDeadlineRepository breakDeadlineRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.propertyRepository = propertyRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.unitRepository = unitRepository;
        this.tenantRepository = tenantRepository;
        this.leaseRepository = leaseRepository;
        this.leaseUnitRepository = leaseUnitRepository;
        this.rentRollRepository = rentRollRepository;
        this.rentArrearsRepository = rentArrearsRepository;
        this.leaseExpiryRepository = leaseExpiryRepository;
        this.indexationDueRepository = indexationDueRepository;
        this.renewalDeadlineRepository = renewalDeadlineRepository;
        this.breakDeadlineRepository = breakDeadlineRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        User elena = ensureUser("Elena Vance", "elena.vance@gmail.com", "elena123");
        User marcus = ensureUser("Marcus Sterling", "marcus.sterling@gmail.com", "marcus123");

        if (propertyRepository.count() != 0) {
            log.info("Development data seeding skipped because properties already exist.");
            return;
        }

        Random random = new Random(42L);
        Map<String, Integer> typeIds = ensurePropertyTypes();
        List<PropertyDraft> propertyDrafts = createProperties(typeIds, elena, marcus, random);
        List<UnitDraft> unitDrafts = createUnits(propertyDrafts, random);

        propertyRepository.saveAll(propertyDrafts.stream().map(PropertyDraft::property).toList());
        List<Unit> units = unitDrafts.stream().map(UnitDraft::unit).toList();
        for (UnitDraft draft : unitDrafts) {
            draft.unit().setPropertyId(draft.property().getId());
        }
        unitRepository.saveAll(units);

        List<Tenant> tenants = createTenants(random);
        tenantRepository.saveAll(tenants);

        List<LeasePlan> leasePlans = createLeasePlans(unitDrafts, tenants, random);
        leaseRepository.saveAll(leasePlans.stream().map(LeasePlan::lease).toList());
        List<LeaseUnit> leaseUnits = createLeaseUnits(leasePlans);
        leaseUnitRepository.saveAll(leaseUnits);

        Map<Integer, IndexationDue> indexations = createIndexations(leasePlans, random);
        List<IndexationDue> indexationRows = new ArrayList<>(indexations.values());
        indexationDueRepository.saveAll(indexationRows);

        List<LeaseExpiry> expiries = createLeaseExpiries(leasePlans);
        leaseExpiryRepository.saveAll(expiries);

        List<RenewalDeadline> renewals = createRenewalDeadlines(leasePlans, random);
        renewalDeadlineRepository.saveAll(renewals);

        List<BreakDeadline> breaks = createBreakDeadlines(leasePlans, random);
        breakDeadlineRepository.saveAll(breaks);

        Map<Integer, List<RentRoll>> rentRollByLease = createRentRoll(leasePlans, indexations, random);
        List<RentRoll> rentRollRows = rentRollByLease.values().stream()
                .flatMap(List::stream)
                .toList();
        rentRollRepository.saveAll(rentRollRows);

        List<RentArrears> arrears = createArrears(rentRollByLease);
        rentArrearsRepository.saveAll(arrears);

        log.info("Development data seeded: {} properties, {} units, {} tenants, {} leases, "
                        + "{} rent-roll rows, {} arrears records.",
                propertyDrafts.size(), units.size(), tenants.size(), leasePlans.size(),
                rentRollRows.size(), arrears.size());
    }

    private User ensureUser(String username, String email, String rawPassword) {
        return userRepository.findByEmail(email).orElseGet(() ->
                userRepository.save(new User(null, username, email,
                        passwordEncoder.encode(rawPassword), CREATED_AT)));
    }

    private Map<String, Integer> ensurePropertyTypes() {
        Map<String, PropertyType> existing = new HashMap<>();
        for (PropertyType type : propertyTypeRepository.findAll()) {
            existing.put(type.getName(), type);
        }

        List<PropertyType> missing = new ArrayList<>();
        for (String name : PROPERTY_TYPES) {
            if (!existing.containsKey(name)) {
                missing.add(new PropertyType(null, name));
            }
        }
        propertyTypeRepository.saveAll(missing);
        for (PropertyType type : missing) {
            existing.put(type.getName(), type);
        }

        Map<String, Integer> ids = new HashMap<>();
        for (String name : PROPERTY_TYPES) {
            ids.put(name, existing.get(name).getId());
        }
        return ids;
    }

    private List<PropertyDraft> createProperties(
            Map<String, Integer> typeIds, User elena, User marcus, Random random) {
        List<PropertyDraft> drafts = new ArrayList<>(120);
        String[] featuredNames = {
                "Apex Tower",
                "Northgate Logistics Hub",
                "Harbor Plaza Retail",
                "Midtown Commerce Center",
                "Bayside Medical Pavilion"
        };
        String[] featuredTypes = {
                "Office", "Industrial / Logistics", "Retail", "Office", "Medical"
        };
        String[] featuredCities = {"Chicago", "Dallas", "Seattle", "Atlanta", "Boston"};
        Set<String> names = new HashSet<>(List.of(featuredNames));

        for (int i = 0; i < 120; i++) {
            int portfolio = i < 65 ? 0 : i < 90 ? 1 : 2;
            String name;
            String typeName;
            String city;
            if (i < featuredNames.length) {
                name = featuredNames[i];
                typeName = featuredTypes[i];
                city = featuredCities[i];
            } else {
                name = uniquePropertyName(names, i - featuredNames.length, random);
                typeName = PROPERTY_TYPES.get(random.nextInt(PROPERTY_TYPES.size()));
                city = CITIES.get(random.nextInt(CITIES.size()));
            }
            String address = (100 + random.nextInt(9_800)) + " "
                    + STREET_NAMES.get(random.nextInt(STREET_NAMES.size())) + " "
                    + STREET_SUFFIXES.get(random.nextInt(STREET_SUFFIXES.size()));
            Integer managerId = portfolio == 0 ? elena.getId()
                    : portfolio == 1 ? marcus.getId() : null;
            Property property = new Property(null, name, address, city, typeIds.get(typeName),
                    managerId, BigDecimal.ZERO.setScale(2), 0, CREATED_AT);
            drafts.add(new PropertyDraft(property, typeName, i));
        }
        return drafts;
    }

    private String uniquePropertyName(Set<String> names, int index, Random random) {
        for (int attempt = 0; attempt < 100; attempt++) {
            String candidate = PROPERTY_NAME_PREFIXES.get(random.nextInt(PROPERTY_NAME_PREFIXES.size()))
                    + " " + PROPERTY_NAME_SUFFIXES.get(random.nextInt(PROPERTY_NAME_SUFFIXES.size()));
            if (names.add(candidate)) {
                return candidate;
            }
        }
        String candidate = PROPERTY_NAME_PREFIXES.get(index % PROPERTY_NAME_PREFIXES.size())
                + " " + PROPERTY_NAME_SUFFIXES.get(index % PROPERTY_NAME_SUFFIXES.size())
                + " " + (index + 2);
        names.add(candidate);
        return candidate;
    }

    private List<UnitDraft> createUnits(List<PropertyDraft> properties, Random random) {
        List<UnitDraft> drafts = new ArrayList<>(1_500);
        for (PropertyDraft propertyDraft : properties) {
            int unitCount = propertyDraft.index() < 5
                    ? 18 : 11 + random.nextInt(3);
            int portfolio = propertyDraft.index() < 65 ? 0
                    : propertyDraft.index() < 90 ? 1 : 2;
            double occupancyRate = portfolio == 0 ? 0.80 : portfolio == 1 ? 0.68 : 0.60;
            int occupiedCount = (int) Math.round(unitCount * occupancyRate);
            List<Integer> occupiedIndexes = new ArrayList<>(unitCount);
            for (int i = 0; i < unitCount; i++) {
                occupiedIndexes.add(i);
            }
            Collections.shuffle(occupiedIndexes, random);
            Set<Integer> occupied = new HashSet<>(occupiedIndexes.subList(0, occupiedCount));

            BigDecimal totalArea = BigDecimal.ZERO.setScale(2);
            for (int i = 0; i < unitCount; i++) {
                BigDecimal area = unitArea(propertyDraft.typeName(), propertyDraft.index(), random);
                totalArea = totalArea.add(area);
                Unit unit = new Unit(null, null,
                        unitNumber(propertyDraft.typeName(), propertyDraft.index(), i),
                        floorNumber(propertyDraft.typeName(), i), area,
                        occupied.contains(i) ? "OCCUPIED" : "VACANT");
                drafts.add(new UnitDraft(unit, propertyDraft.property(), propertyDraft.typeName(),
                        propertyDraft.index()));
            }
            propertyDraft.property().setNumberOfUnits(unitCount);
            propertyDraft.property().setTotalArea(totalArea);
        }
        return drafts;
    }

    private BigDecimal unitArea(String type, int propertyIndex, Random random) {
        int min;
        int range;
        if (propertyIndex < 5) {
            switch (type) {
                case "Office" -> {
                    min = 5_200;
                    range = 2_000;
                }
                case "Industrial / Logistics" -> {
                    min = 13_000;
                    range = 5_000;
                }
                case "Retail" -> {
                    min = 4_000;
                    range = 2_000;
                }
                default -> {
                    min = 4_500;
                    range = 2_000;
                }
            }
        } else {
            switch (type) {
                case "Office" -> {
                    min = 1_300;
                    range = 2_400;
                }
                case "Industrial / Logistics" -> {
                    min = 2_500;
                    range = 6_000;
                }
                case "Retail" -> {
                    min = 900;
                    range = 2_600;
                }
                default -> {
                    min = 900;
                    range = 1_900;
                }
            }
        }
        return BigDecimal.valueOf(min + random.nextInt(range)).setScale(2);
    }

    private String unitNumber(String type, int propertyIndex, int index) {
        if (type.equals("Industrial / Logistics")) {
            return "Bay " + (char) ('A' + index);
        }
        if (type.equals("Office") && (propertyIndex == 0 || propertyIndex == 3)) {
            return "Floor " + (index + 1) + " - " + (index % 2 == 0 ? "East" : "West");
        }
        int number = (type.equals("Medical") ? 200 : 100) + index * 10 + 1;
        return "Suite " + number;
    }

    private Integer floorNumber(String type, int index) {
        if (type.equals("Industrial / Logistics")) {
            return 1;
        }
        return Math.max(1, index / (type.equals("Office") ? 2 : 4) + 1);
    }

    private List<Tenant> createTenants(Random random) {
        List<Tenant> tenants = new ArrayList<>(580);
        for (int i = 0; i < 580; i++) {
            String prefix = COMPANY_PREFIXES.get(i % COMPANY_PREFIXES.size());
            String suffix = COMPANY_SUFFIXES.get(i / COMPANY_PREFIXES.size());
            String company = prefix + " " + suffix;
            String slug = company.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]+", "");
            String contact = FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size())) + " "
                    + LAST_NAMES.get(random.nextInt(LAST_NAMES.size()));
            String email = "leasing@" + slug + ".com";
            String phone = "+1 (" + PHONE_AREA_CODES.get(i % PHONE_AREA_CODES.size())
                    + ") 555-" + String.format(Locale.ROOT, "%04d", 1000 + i);
            String taxId = String.format(Locale.ROOT, "%02d-%07d", 10 + i % 90, 1_000_000 + i);
            tenants.add(new Tenant(null, company, contact, email, phone, taxId, CREATED_AT));
        }
        return tenants;
    }

    private List<LeasePlan> createLeasePlans(
            List<UnitDraft> unitDrafts, List<Tenant> tenants, Random random) {
        Map<Integer, List<UnitDraft>> byProperty = new LinkedHashMap<>();
        for (UnitDraft draft : unitDrafts) {
            if (draft.unit().getStatus().equals("OCCUPIED")) {
                byProperty.computeIfAbsent(draft.property().getId(), ignored -> new ArrayList<>())
                        .add(draft);
            }
        }

        List<LeasePlan> plans = new ArrayList<>();
        for (List<UnitDraft> occupiedUnits : byProperty.values()) {
            Collections.shuffle(occupiedUnits, random);
            for (int i = 0; i < occupiedUnits.size();) {
                UnitDraft first = occupiedUnits.get(i++);
                List<Unit> leasedUnits = new ArrayList<>(2);
                leasedUnits.add(first.unit());
                if (i < occupiedUnits.size() && random.nextDouble() < 0.12) {
                    leasedUnits.add(occupiedUnits.get(i++).unit());
                }
                int durationDays = sampleDurationDays(random);
                int elapsedDays = random.nextInt(Math.min(durationDays - 1, 1_800) + 1);
                LocalDate startDate = REFERENCE_DATE.minusDays(elapsedDays);
                LocalDate endDate = startDate.plusDays(durationDays);
                BigDecimal rent = rentForUnits(leasedUnits, first.typeName(), random, first.propertyIndex());
                Lease lease = new Lease(null, first.property().getId(),
                        tenants.get(random.nextInt(tenants.size())).getId(), startDate, endDate, rent,
                        rent.multiply(BigDecimal.valueOf(2 + random.nextInt(2)))
                                .setScale(2, RoundingMode.HALF_UP),
                        "ACTIVE", CREATED_AT);
                plans.add(new LeasePlan(lease, leasedUnits, true, durationDays));
            }
        }

        List<LeasePlan> activePlans = new ArrayList<>(plans);
        Collections.shuffle(activePlans, random);
        int expiredCount = (int) Math.round(activePlans.size() * 0.22);
        for (int i = 0; i < expiredCount; i++) {
            LeasePlan priorLease = activePlans.get(i);
            Unit unit = priorLease.units().get(0);
            UnitDraft unitDraft = unitDrafts.stream()
                    .filter(draft -> draft.unit() == unit)
                    .findFirst()
                    .orElseThrow();
            LocalDate endDate = priorLease.lease().getStartDate().minusDays(1 + random.nextInt(61));
            int durationDays = sampleDurationDays(random);
            LocalDate startDate = endDate.minusDays(durationDays);
            BigDecimal rent = rentForUnits(List.of(unit), unitDraft.typeName(), random,
                    unitDraft.propertyIndex());
            Lease lease = new Lease(null, priorLease.lease().getPropertyId(),
                    tenants.get(random.nextInt(tenants.size())).getId(), startDate, endDate, rent,
                    rent.multiply(BigDecimal.valueOf(2 + random.nextInt(2)))
                            .setScale(2, RoundingMode.HALF_UP),
                    "EXPIRED", CREATED_AT);
            plans.add(new LeasePlan(lease, List.of(unit), false, durationDays));
        }
        return plans;
    }

    private BigDecimal rentForUnits(
            List<Unit> units, String type, Random random, int propertyIndex) {
        BigDecimal monthlyRate = switch (type) {
            case "Office" -> BigDecimal.valueOf(propertyIndex < 5 ? 3.80 : 2.90);
            case "Industrial / Logistics" -> BigDecimal.valueOf(propertyIndex < 5 ? 1.45 : 1.25);
            case "Retail" -> BigDecimal.valueOf(propertyIndex < 5 ? 3.20 : 2.65);
            default -> BigDecimal.valueOf(propertyIndex < 5 ? 4.40 : 3.65);
        };
        BigDecimal area = units.stream().map(Unit::getArea)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal marketVariation = BigDecimal.valueOf(95 + random.nextInt(11), 2);
        return area.multiply(monthlyRate).multiply(marketVariation)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private List<LeaseUnit> createLeaseUnits(List<LeasePlan> plans) {
        List<LeaseUnit> joins = new ArrayList<>();
        for (LeasePlan plan : plans) {
            for (Unit unit : plan.units()) {
                joins.add(new LeaseUnit(null, plan.lease().getId(), unit.getId()));
            }
        }
        return joins;
    }

    private Map<Integer, IndexationDue> createIndexations(
            List<LeasePlan> plans, Random random) {
        Map<Integer, IndexationDue> indexations = new LinkedHashMap<>();
        for (LeasePlan plan : plans) {
            if (plan.durationDays() <= 365 || random.nextDouble() >= 0.35) {
                continue;
            }
            Lease lease = plan.lease();
            LocalDate indexationDate = lease.getStartDate().plusDays(365);
            long daysBefore = 30 + random.nextInt(31);
            LocalDate notificationDeadline = indexationDate.minusDays(daysBefore);
            BigDecimal percentage = BigDecimal.valueOf(250 + random.nextInt(201), 2);
            BigDecimal newRent = lease.getInitialRentAmount()
                    .multiply(BigDecimal.ONE.add(percentage.movePointLeft(2)))
                    .setScale(2, RoundingMode.HALF_UP);
            String status = dateStatus(indexationDate, 90);
            indexations.put(lease.getId(), new IndexationDue(null, lease.getId(),
                    notificationDeadline, indexationDate, percentage, newRent, status));
        }
        return indexations;
    }

    private List<LeaseExpiry> createLeaseExpiries(List<LeasePlan> plans) {
        List<LeaseExpiry> expiries = new ArrayList<>();
        for (LeasePlan plan : plans) {
            if (!plan.active()) {
                continue;
            }
            LocalDate expiry = plan.lease().getEndDate();
            long daysUntil = ChronoUnit.DAYS.between(REFERENCE_DATE, expiry);
            String status = daysUntil <= 30 ? "URGENT"
                    : daysUntil <= 90 ? "ACTION_REQUIRED"
                    : daysUntil <= 180 ? "UPCOMING" : "MONITOR";
            expiries.add(new LeaseExpiry(null, plan.lease().getId(), expiry, status));
        }
        return expiries;
    }

    private List<RenewalDeadline> createRenewalDeadlines(
            List<LeasePlan> plans, Random random) {
        List<RenewalDeadline> renewals = new ArrayList<>();
        for (LeasePlan plan : plans) {
            if (!plan.active() || random.nextDouble() >= 0.20) {
                continue;
            }
            LocalDate effectiveDate = plan.lease().getEndDate();
            LocalDate decisionDeadline = effectiveDate.minusDays(90 + random.nextInt(91));
            String status = decisionDeadline.isBefore(REFERENCE_DATE) ? "OVERDUE"
                    : ChronoUnit.DAYS.between(REFERENCE_DATE, decisionDeadline) <= 60
                    ? "DUE" : "UPCOMING";
            renewals.add(new RenewalDeadline(null, plan.lease().getId(), decisionDeadline,
                    effectiveDate, status));
        }
        return renewals;
    }

    private List<BreakDeadline> createBreakDeadlines(
            List<LeasePlan> plans, Random random) {
        List<BreakDeadline> breaks = new ArrayList<>();
        for (LeasePlan plan : plans) {
            if (plan.durationDays() < 730 || random.nextDouble() >= 0.15) {
                continue;
            }
            LocalDate effectiveDate = plan.lease().getStartDate().plusDays(plan.durationDays() / 2L);
            LocalDate windowStart = effectiveDate.minusDays(30);
            LocalDate windowEnd = effectiveDate.plusDays(30);
            String status = REFERENCE_DATE.isBefore(windowStart) ? "UPCOMING"
                    : REFERENCE_DATE.isAfter(windowEnd) ? "PASSED" : "IN_WINDOW";
            breaks.add(new BreakDeadline(null, plan.lease().getId(), windowStart, windowEnd,
                    effectiveDate, status));
        }
        return breaks;
    }

    private Map<Integer, List<RentRoll>> createRentRoll(
            List<LeasePlan> plans, Map<Integer, IndexationDue> indexations, Random random) {
        Map<Integer, List<RentRoll>> rowsByLease = new LinkedHashMap<>();
        LocalDate firstDueDate = LocalDate.of(2025, 12, 1);
        for (LeasePlan plan : plans) {
            Lease lease = plan.lease();
            IndexationDue indexation = indexations.get(lease.getId());
            List<RentRoll> leaseRows = new ArrayList<>();
            for (int month = 0; month < 12; month++) {
                LocalDate dueDate = firstDueDate.plusMonths(month);
                if (dueDate.isBefore(lease.getStartDate()) || dueDate.isAfter(lease.getEndDate())) {
                    continue;
                }
                BigDecimal amountDue = indexation != null
                        && !dueDate.isBefore(indexation.getIndexationDate())
                        ? indexation.getNewRentAmount() : lease.getInitialRentAmount();
                double overdueRate = month >= 9 ? 0.14 : 0.04;
                double partialRate = 0.08;
                double draw = random.nextDouble();
                String status;
                BigDecimal amountPaid;
                LocalDate paymentDate;
                if (draw < overdueRate) {
                    status = "OVERDUE";
                    amountPaid = BigDecimal.ZERO.setScale(2);
                    paymentDate = null;
                } else if (draw < overdueRate + partialRate) {
                    status = "PARTIAL";
                    BigDecimal paidRatio = BigDecimal.valueOf(40 + random.nextInt(41), 2);
                    amountPaid = amountDue.multiply(paidRatio).setScale(2, RoundingMode.HALF_UP);
                    paymentDate = dueDate.plusDays(1 + random.nextInt(12));
                } else {
                    status = "PAID";
                    amountPaid = amountDue;
                    paymentDate = dueDate;
                }
                leaseRows.add(new RentRoll(null, lease.getId(), dueDate, amountDue, amountPaid,
                        paymentDate, status));
            }
            rowsByLease.put(lease.getId(), leaseRows);
        }
        return rowsByLease;
    }

    private List<RentArrears> createArrears(Map<Integer, List<RentRoll>> rowsByLease) {
        List<RentArrears> arrears = new ArrayList<>();
        for (Map.Entry<Integer, List<RentRoll>> entry : rowsByLease.entrySet()) {
            List<RentRoll> unpaidRows = entry.getValue().stream()
                    .filter(row -> row.getStatus().equals("OVERDUE") || row.getStatus().equals("PARTIAL"))
                    .toList();
            if (unpaidRows.isEmpty()) {
                continue;
            }
            BigDecimal owed = unpaidRows.stream()
                    .map(row -> row.getAmountDue().subtract(row.getAmountPaid()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .setScale(2, RoundingMode.HALF_UP);
            LocalDate defaultDate = unpaidRows.stream()
                    .map(RentRoll::getDueDate)
                    .min(LocalDate::compareTo)
                    .orElseThrow();
            int daysOverdue = Math.toIntExact(ChronoUnit.DAYS.between(defaultDate, REFERENCE_DATE));
            arrears.add(new RentArrears(null, entry.getKey(), defaultDate, daysOverdue, owed,
                    defaultDate.plusDays(30), "OPEN"));
        }
        return arrears;
    }

    private String dateStatus(LocalDate date, int dueWithinDays) {
        if (date.isBefore(REFERENCE_DATE)) {
            return "COMPLETED";
        }
        return ChronoUnit.DAYS.between(REFERENCE_DATE, date) <= dueWithinDays
                ? "DUE" : "UPCOMING";
    }

    private int sampleDurationDays(Random random) {
        double term = random.nextDouble();
        if (term < 0.43) {
            return 730 + random.nextInt(1_096);
        }
        if (term < 0.78) {
            return 365 + random.nextInt(365);
        }
        return 60 + random.nextInt(305);
    }

    private record PropertyDraft(Property property, String typeName, int index) {}

    private record UnitDraft(Unit unit, Property property, String typeName, int propertyIndex) {}

    private record LeasePlan(Lease lease, List<Unit> units, boolean active, int durationDays) {}
}
