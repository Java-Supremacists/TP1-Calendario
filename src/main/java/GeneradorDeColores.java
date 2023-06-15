/**
 * GeneradorDeColores
 */
public class GeneradorDeColores {

    public static String getRandomColor() {
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        return String.format("#%02X%02X%02X",
                             (int) (red * 255),
                             (int) (green * 255),
                             (int) (blue * 255));
    }
    
    public static String getColorFromId(int id) {
	var representacionHexa = Integer.toHexString(id);
	int resultRed = Integer.valueOf(representacionHexa.substring(0, 2), 16);
	int resultGreen = Integer.valueOf(representacionHexa.substring(2, 4), 16);
	int resultBlue = Integer.valueOf(representacionHexa.substring(4, 6), 16);
        return String.format("#%02X%02X%02X",
		resultRed,
		resultGreen,
		resultBlue);
    }
}
