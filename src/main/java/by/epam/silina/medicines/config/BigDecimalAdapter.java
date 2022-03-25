package by.epam.silina.medicines.config;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static by.epam.silina.medicines.config.Constant.NUMBER_OF_DECIMAL_PLACE;

public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {
    @Override
    public BigDecimal unmarshal(String string) {
        BigDecimal bigDecimal = BigDecimal.valueOf(Double.parseDouble(string));
        return bigDecimal.setScale(NUMBER_OF_DECIMAL_PLACE, RoundingMode.FLOOR);
    }

    @Override
    public String marshal(BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            return bigDecimal.toString();
        }
        return null;
    }
}
