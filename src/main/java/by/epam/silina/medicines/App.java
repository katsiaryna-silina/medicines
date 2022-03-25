package by.epam.silina.medicines;

import by.epam.silina.medicines.util.impl.DOMParsingUtil;
import by.epam.silina.medicines.util.impl.JAXBUtilImpl;
import by.epam.silina.medicines.util.impl.SAXParsingUtil;
import by.epam.silina.medicines.util.impl.StAXParsingUtil;

import static by.epam.silina.medicines.config.Constant.MEDICINES_XML_PATH;
import static by.epam.silina.medicines.config.Constant.USERS_XML_PATH;

public class App {
    public static void main(String[] args) {
        SAXParsingUtil.getInstance().parse(USERS_XML_PATH);

        SAXParsingUtil.getInstance().parse(MEDICINES_XML_PATH);

        StAXParsingUtil.getInstance().parse(MEDICINES_XML_PATH);

        JAXBUtilImpl.getInstance().convert(MEDICINES_XML_PATH);

        DOMParsingUtil.getInstance().parse(MEDICINES_XML_PATH);
    }
}
