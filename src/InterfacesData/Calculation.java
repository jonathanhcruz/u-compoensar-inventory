package InterfacesData;

import java.util.HashMap;
import java.util.Map;

public class Calculation {
    public static  Map<String, Double> calculateDiscount (int timeWorked) {
      Map<String, Double> discountMap = new HashMap<>();

      if (timeWorked <= 1) {
         discountMap.put("tienda", 0.15);
         discountMap.put("recreacion", 0.2);
      } else if (timeWorked <= 6) {
        discountMap.put("tienda", 0.3);
        discountMap.put("recreacion", 0.3);
      }

      discountMap.put("tienda", 0.5);
      discountMap.put("recreacion", 0.6);

      return discountMap;
    }

    public static double calculateIva (double price, int typeOfProduct) {
        double iva = price * 0.1;

        if (typeOfProduct == 1) { // Aseo
            iva = price * 0.19;
        } else if (typeOfProduct == 2) { // Papeleria
            iva = price * 0.09;
        } else if (typeOfProduct == 3) { // Producto para mascotas
            iva = price * 0.16;
        } else if (typeOfProduct == 4) { // Vivieres
            iva = price * 0.15;
        }

        return iva; // Otros
    }
}
