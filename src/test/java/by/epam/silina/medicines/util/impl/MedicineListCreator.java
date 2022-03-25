package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.model.medicines.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static by.epam.silina.medicines.config.Constant.NUMBER_OF_DECIMAL_PLACE;

public class MedicineListCreator {

    public List<Medicine> createMedicineList() {
        List<Medicine> medicineList = new ArrayList<>();
        List<MedicinePackage> medicinePackagesOne = new ArrayList<>();
        MedicinePackage medicinePackageOne = MedicinePackage.builder()
                .packageTypeEnum(PackageTypeEnum.PACK)
                .number(16)
                .price(BigDecimal.valueOf(19.6).setScale(2, RoundingMode.FLOOR))
                .isSealed(false)
                .build();
        medicinePackagesOne.add(medicinePackageOne);

        Certificate certificateOne = Certificate.builder()
                .name("Registration certificate")
                .whoIssued("Ministry of Health of the Republic of Belarus")
                .registrationNumber("6664/04/07/09/12/14/17/19")
                .registrationDateFrom(LocalDate.parse("2019-08-02"))
                .registrationDateTo(LocalDate.parse("2024-08-02"))
                .build();

        List<Version> versionsOne = new ArrayList<>();
        Version versionOne = Version.builder()
                .medicineTypeEnum(MedicineTypeEnum.EFFERVESCENT_TABLETS)
                .dosage("500 mg")
                .certificate(certificateOne)
                .medicinePackages(medicinePackagesOne)
                .build();
        versionsOne.add(versionOne);

        List<MedicinePackage> medicinePackagesTwo = new ArrayList<>();
        MedicinePackage medicinePackageTwo = MedicinePackage.builder()
                .packageTypeEnum(PackageTypeEnum.TUBE)
                .number(10)
                .price(BigDecimal.valueOf(8.02).setScale(2, RoundingMode.FLOOR))
                .isSealed(true)
                .build();
        medicinePackagesTwo.add(medicinePackageTwo);

        Certificate certificateTwo = Certificate.builder()
                .name("Registration certificate")
                .whoIssued("Ministry of Health of the Republic of Belarus")
                .registrationNumber("1610/96/01/06/10/12/16/19")
                .registrationDateFrom(LocalDate.parse("2016-06-06"))
                .registrationDateTo(LocalDate.parse("2026-06-06"))
                .build();

        Version versionTwo = Version.builder()
                .medicineTypeEnum(MedicineTypeEnum.EFFERVESCENT_TABLETS)
                .dosage("500 mg")
                .certificate(certificateTwo)
                .medicinePackages(medicinePackagesTwo)
                .build();
        versionsOne.add(versionTwo);

        Medicine medicineExceptedOne = Medicine.builder()
                .id(UUID.fromString("a23e4567-e89b-12d3-a456-426614174000"))
                .name("Upsarin Upsa")
                .company("UPSA, SAS.")
                .group("NSAID")
                .versions(versionsOne)
                .build();
        medicineList.add(medicineExceptedOne);


        List<MedicinePackage> medicinePackagesTree = new ArrayList<>();
        MedicinePackage medicinePackageTree = MedicinePackage.builder()
                .packageTypeEnum(PackageTypeEnum.PACK)
                .number(10)
                .price(BigDecimal.valueOf(9.0).setScale(2, RoundingMode.FLOOR))
                .isSealed(false)
                .build();
        medicinePackagesTree.add(medicinePackageTree);

        Certificate certificateTree = Certificate.builder()
                .name("Registration certificate")
                .whoIssued("Ministry of Health of the Republic of Belarus")
                .registrationNumber("2459/96/02/07/12/17")
                .registrationDateFrom(LocalDate.parse("2017-10-30"))
                .registrationDateTo(LocalDate.parse("2022-10-30"))
                .build();

        List<Version> versions = new ArrayList<>();
        Version versionTree = Version.builder()
                .medicineTypeEnum(MedicineTypeEnum.EFFERVESCENT_TABLETS)
                .dosage("400 mg")
                .certificate(certificateTree)
                .medicinePackages(medicinePackagesTree)
                .build();
        versions.add(versionTree);

        Medicine medicineExceptedTwo = Medicine.builder()
                .id(UUID.fromString("a0859413-d191-4c58-92dd-664e3f124737"))
                .name("Aspirin-C")
                .company("BAYER BITTERFELD, GmbH")
                .group("NSAID")
                .versions(versions)
                .build();
        medicineList.add(medicineExceptedTwo);


        List<MedicinePackage> medicinePackagesFour = new ArrayList<>();
        MedicinePackage medicinePackageFour = MedicinePackage.builder()
                .packageTypeEnum(PackageTypeEnum.PACK)
                .number(10)
                .price(BigDecimal.valueOf(11.22).setScale(NUMBER_OF_DECIMAL_PLACE, RoundingMode.FLOOR))
                .isSealed(false)
                .build();
        medicinePackagesFour.add(medicinePackageFour);

        Certificate certificateFour = Certificate.builder()
                .name("Registration certificate")
                .whoIssued("Ministry of Health of the Republic of Belarus")
                .registrationNumber("454/94/04/07/09/14/19")
                .registrationDateFrom(LocalDate.parse("2017-10-31"))
                .registrationDateTo(LocalDate.parse("2022-10-31"))
                .build();

        List<Version> versionsFour = new ArrayList<>();
        Version versionFour = Version.builder()
                .medicineTypeEnum(MedicineTypeEnum.EFFERVESCENT_TABLETS)
                .dosage("324 mg")
                .certificate(certificateFour)
                .medicinePackages(medicinePackagesFour)
                .build();
        versionsFour.add(versionFour);

        Medicine medicineExceptedTree = Medicine.builder()
                .id(UUID.fromString("aa28996f-2f7b-40d0-a99a-14c66199ac61"))
                .name("Alka-Seltze")
                .company("BAYER BITTERFELD, GmbH")
                .group("NSAID")
                .versions(versionsFour)
                .build();
        medicineList.add(medicineExceptedTree);


        List<MedicinePackage> medicinePackagesFive = new ArrayList<>();
        MedicinePackage medicinePackageSix = MedicinePackage.builder()
                .packageTypeEnum(PackageTypeEnum.JAR)
                .number(100)
                .price(BigDecimal.valueOf(18.03).setScale(NUMBER_OF_DECIMAL_PLACE, RoundingMode.FLOOR))
                .isSealed(false)
                .build();
        medicinePackagesFive.add(medicinePackageSix);
        MedicinePackage medicinePackageFive = MedicinePackage.builder()
                .packageTypeEnum(PackageTypeEnum.JAR)
                .number(30)
                .price(BigDecimal.valueOf(9.10).setScale(NUMBER_OF_DECIMAL_PLACE, RoundingMode.FLOOR))
                .isSealed(false)
                .build();
        medicinePackagesFive.add(medicinePackageFive);

        Certificate certificateFive = Certificate.builder()
                .name("Registration certificate")
                .whoIssued("Ministry of Health of the Republic of Belarus")
                .registrationNumber("452/02/09/01/24/11")
                .registrationDateFrom(LocalDate.parse("2019-12-21"))
                .registrationDateTo(LocalDate.parse("2024-12-21"))
                .build();

        List<Version> versionsFive = new ArrayList<>();
        Version versionFive = Version.builder()
                .medicineTypeEnum(MedicineTypeEnum.TABLETS)
                .dosage("75 mg")
                .certificate(certificateFive)
                .medicinePackages(medicinePackagesFive)
                .build();
        versionsFive.add(versionFive);

        Medicine medicineExceptedFour = Medicine.builder()
                .id(UUID.fromString("aa121538-b609-401a-ab03-c264e2f27550"))
                .name("Cardiomagnyl")
                .company("TAKEDA, GmbH")
                .group("NSAID")
                .versions(versionsFive)
                .build();
        medicineList.add(medicineExceptedFour);


        List<MedicinePackage> medicinePackagesSix = new ArrayList<>();
        MedicinePackage medicinePackageSeven = MedicinePackage.builder()
                .packageTypeEnum(PackageTypeEnum.PACK)
                .number(30)
                .price(BigDecimal.valueOf(34.06).setScale(NUMBER_OF_DECIMAL_PLACE, RoundingMode.FLOOR))
                .isSealed(false)
                .build();
        medicinePackagesSix.add(medicinePackageSeven);

        Certificate certificateSix = Certificate.builder()
                .name("Registration certificate")
                .whoIssued("Ministry of Health of the Republic of Belarus")
                .registrationNumber("1136/95/01/06/11/17")
                .registrationDateFrom(LocalDate.parse("2017-01-13"))
                .registrationDateTo(LocalDate.parse("2023-01-13"))
                .build();

        List<Version> versionsSix = new ArrayList<>();
        Version versionSix = Version.builder()
                .medicineTypeEnum(MedicineTypeEnum.CAPSULES)
                .dosage("20 mg")
                .certificate(certificateSix)
                .medicinePackages(medicinePackagesSix)
                .build();
        versionsSix.add(versionSix);

        List<MedicinePackage> medicinePackagesSeven = new ArrayList<>();
        MedicinePackage medicinePackageEight = MedicinePackage.builder()
                .packageTypeEnum(PackageTypeEnum.PACK)
                .number(30)
                .price(BigDecimal.valueOf(23.82).setScale(NUMBER_OF_DECIMAL_PLACE, RoundingMode.FLOOR))
                .isSealed(false)
                .build();
        medicinePackagesSeven.add(medicinePackageEight);

        Certificate certificateSeven = Certificate.builder()
                .name("Registration certificate")
                .whoIssued("Ministry of Health of the Republic of Belarus")
                .registrationNumber("8956/09/10/10/11/14/17/19/21/21")
                .registrationDateFrom(LocalDate.parse("2019-08-29"))
                .registrationDateTo(LocalDate.parse("2024-08-29"))
                .build();

        Version versionSeven = Version.builder()
                .medicineTypeEnum(MedicineTypeEnum.CAPSULES)
                .dosage("10 mg")
                .certificate(certificateSeven)
                .medicinePackages(medicinePackagesSeven)
                .build();
        versionsSix.add(versionSeven);

        List<MedicinePackage> medicinePackagesEight = new ArrayList<>();
        MedicinePackage medicinePackageNine = MedicinePackage.builder()
                .packageTypeEnum(PackageTypeEnum.PACK)
                .number(30)
                .price(BigDecimal.valueOf(49.5).setScale(NUMBER_OF_DECIMAL_PLACE, RoundingMode.FLOOR))
                .isSealed(false)
                .build();
        medicinePackagesEight.add(medicinePackageNine);

        Certificate certificateEight = Certificate.builder()
                .name("Registration certificate")
                .whoIssued("Ministry of Health of the Republic of Belarus")
                .registrationNumber("9346/02/10/10/11/14/17/20/22/22")
                .registrationDateFrom(LocalDate.parse("2020-04-12"))
                .registrationDateTo(LocalDate.parse("2025-04-12"))
                .build();

        Version versionEight = Version.builder()
                .medicineTypeEnum(MedicineTypeEnum.CAPSULES)
                .dosage("40 mg")
                .certificate(certificateEight)
                .medicinePackages(medicinePackagesEight)
                .build();
        versionsSix.add(versionEight);

        Medicine medicineExceptedFive = Medicine.builder()
                .id(UUID.fromString("ec9fbb2c-a1e8-4a6b-9da8-056448a37797"))
                .name("Omez")
                .company("Dr. Reddy's Laboratories, LTD")
                .group("proton pump inhibitor")
                .versions(versionsSix)
                .build();
        medicineList.add(medicineExceptedFive);


        List<MedicinePackage> medicinePackagesNine = new ArrayList<>();
        MedicinePackage medicinePackageTen = MedicinePackage.builder()
                .packageTypeEnum(PackageTypeEnum.PACK)
                .number(30)
                .price(BigDecimal.valueOf(34.32).setScale(NUMBER_OF_DECIMAL_PLACE, RoundingMode.FLOOR))
                .isSealed(false)
                .build();
        medicinePackagesNine.add(medicinePackageTen);

        Certificate certificateNine = Certificate.builder()
                .name("Registration certificate")
                .whoIssued("Ministry of Health of the Republic of Belarus")
                .registrationNumber("6217/03/07/08/13/18")
                .registrationDateFrom(LocalDate.parse("2018-12-03"))
                .registrationDateTo(LocalDate.parse("2023-12-03"))
                .build();

        List<Version> versionsSeven = new ArrayList<>();
        Version versionNine = Version.builder()
                .medicineTypeEnum(MedicineTypeEnum.CAPSULES)
                .dosage("20 mg")
                .certificate(certificateNine)
                .medicinePackages(medicinePackagesNine)
                .build();
        versionsSeven.add(versionNine);

        Medicine medicineExceptedSix = Medicine.builder()
                .id(UUID.fromString("ecf56b7f-4653-4911-bfbe-588aed33ef74"))
                .name("Omeprez")
                .company("S.C. Rompharm Company S.R.L.")
                .group("proton pump inhibitor")
                .versions(versionsSeven)
                .build();
        medicineList.add(medicineExceptedSix);
        return medicineList;
    }
}
