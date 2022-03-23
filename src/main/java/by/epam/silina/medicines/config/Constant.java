package by.epam.silina.medicines.config;

public final class Constant {
    public static final String MEDICINE_XML_PATH = "src/main/resources/medicines.xml";
    public static final String USERS_XML_PATH = "src/main/resources/users.xml";

    public static final String EFFERVESCENT_TABLETS_TYPE = "effervescent tablets";
    public static final String CAPSULES_TYPE = "capsules";
    public static final String TABLETS_TYPE = "tablets";

    public static final String TUBE_TYPE = "tube";
    public static final String BOTTLE_TYPE = "bottle";
    public static final String JAR_TYPE = "jar";
    public static final String PACK_TYPE = "pack";

    public static final String ID = "id";
    public static final String MEDICINES = "medicines";
    public static final String MEDICINE = "medicine";
    public static final String NAME = "name";
    public static final String COMPANY = "company";
    public static final String GROUP = "group";
    public static final String VERSIONS = "versions";
    public static final String VERSION = "version";
    public static final String MEDICINE_TYPE = "medicineType";
    public static final String PACKAGES = "packages";
    public static final String PACKAGE = "package";
    public static final String IS_SEALED = "isSealed";
    public static final String PACKAGE_TYPE = "packageType";
    public static final String NUMBER = "number";
    public static final String PRICE = "price";
    public static final String DOSAGE = "dosage";
    public static final String CERTIFICATE = "certificate";
    public static final String CERTIFICATE_NAME = "certificateName";
    public static final String WHO_ISSUED = "whoIssued";
    public static final String REGISTRATION_NUMBER = "registrationNumber";
    public static final String REGISTRATION_DATE_FROM = "registrationDateFrom";
    public static final String REGISTRATION_DATE_TO = "registrationDateTo";

    public static final String MDC_MEDICINES = "mdc:medicines";
    public static final String MDC_MEDICINE = "mdc:medicine";
    public static final String MDC_NAME = "mdc:name";
    public static final String MDC_COMPANY = "mdc:company";
    public static final String MDC_GROUP = "mdc:group";
    public static final String MDC_VERSIONS = "mdc:versions";
    public static final String MDC_VERSION = "mdc:version";
    public static final String MDC_MEDICINE_TYPE = "mdc:medicineType";
    public static final String MDC_PACKAGES = "mdc:packages";
    public static final String MDC_PACKAGE = "mdc:package";
    public static final String MDC_IS_SEALED = "mdc:isSealed";
    public static final String MDC_PACKAGE_TYPE = "mdc:packageType";
    public static final String MDC_NUMBER = "mdc:number";
    public static final String MDC_PRICE = "mdc:price";
    public static final String MDC_DOSAGE = "mdc:dosage";
    public static final String MDC_CERTIFICATE = "mdc:certificate";
    public static final String MDC_CERTIFICATE_NAME = "mdc:certificateName";
    public static final String MDC_WHO_ISSUED = "mdc:whoIssued";
    public static final String MDC_REGISTRATION_NUMBER = "mdc:registrationNumber";
    public static final String MDC_REGISTRATION_DATE_FROM = "mdc:registrationDateFrom";
    public static final String MDC_REGISTRATION_DATE_TO = "mdc:registrationDateTo";

    public static final String START_STRING_CLIENT = "client:";
    public static final String START_STRING_EMPLOYEE = "employee:";

    public static final String CLIENT_TELEPHONE_NUMBER = "client:telephoneNumber";
    public static final String EMPLOYEE_POSITION = "employee:position";
    public static final String USERS_USERS = "users:users";
    public static final String USERS_CLIENT = "users:client";
    public static final String USERS_EMPLOYEE = "users:employee";
    public static final String CLIENT_USERNAME = "client:username";
    public static final String CLIENT_PASSWORD = "client:password";
    public static final String CLIENT_EMAIL = "client:email";
    public static final String EMPLOYEE_USERNAME = "employee:username";
    public static final String EMPLOYEE_PASSWORD = "employee:password";
    public static final String EMPLOYEE_EMAIL = "employee:email";


    public static final String FILE_IS_NULL = "File is null.";
    public static final String TELEPHONE_NUMBER_IS_NULL = "Telephone number is null.";
    public static final String EMAIL_IS_NULL = "Email is null.";
    public static final String ID_IS_NULL = "ID is null.";
    public static final String MEDICINE_NAME_IS_NULL = "Medicine name is null.";
    public static final String COMPANY_IS_NULL = "Company is null.";
    public static final String VERSIONS_ARE_NULL = "Versions are null.";
    public static final String MEDICINE_TYPE_ENUM_IS_NULL = "MedicineTypeEnum is null.";
    public static final String DOSAGE_IS_NULL = "Dosage is null.";
    public static final String CERTIFICATE_IS_NULL = "Certificate is null.";
    public static final String MEDICINE_PACKAGES_ARE_NULL = "Medicine packages are null";
    public static final String CERTIFICATE_NAME_IS_NULL = "Certificate name is null.";

    public static final String REGEX_FOR_CHECKING_EMAIL = "[\\d\\D]*@[\\d\\D]*\\.[\\d\\D]*";
    public static final String REGEX_FOR_CHECKING_TELEPHONE_NUMBER = "\\+[0-9]*";
    public static final String REGEX_FOR_CHECKING_DOSAGE = "[0-9]* [a-zA-Z]*";
    public static final int TELEPHONE_NUMBER_MAX_LENGTH = 16;
    public static final int TELEPHONE_NUMBER_MIN_LENGTH = 13;
    public static final int EMAIL_MAX_LENGTH = 30;
    public static final int EMAIL_MIN_LENGTH = 6;
    public static final int USERNAME_MAX_LENGTH = 15;
    public static final int USERNAME_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 25;
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int DOSAGE_MAX_LENGTH = 15;
    public static final int DOSAGE_MIN_LENGTH = 3;
    public static final int MAX_TEXT_LENGTH = 50;
    public static final int MIN_TEXT_LENGTH = 3;

    private Constant() {
    }
}
