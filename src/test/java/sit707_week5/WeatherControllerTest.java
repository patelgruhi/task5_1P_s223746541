package sit707_week5;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherControllerTest {

    private static WeatherController wController;
    private static double[] hourlyTemperatures;

    @BeforeClass
    public static void initializeTestEnvironment() {
        wController = WeatherController.getInstance();
        int nHours = wController.getTotalHours();
        hourlyTemperatures = new double[nHours];
        for (int i = 0; i < nHours; i++) {
            hourlyTemperatures[i] = wController.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void tearDownTestEnvironment() {
        wController.close();
    }


    @Test
    public void testStudentIdentity() {
        String studentId = "s223746541";
        Assert.assertNotNull(studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "gruhi";
        Assert.assertNotNull(studentName);
    }

    @Test
    public void testTemperatureMin() {
        double minTemperature = findMinTemperature(hourlyTemperatures);
        Assert.assertEquals(minTemperature, wController.getTemperatureMinFromCache(), 0.01);
    }

    @Test
    public void testTemperatureMax() {
        double maxTemperature = findMaxTemperature(hourlyTemperatures);
        Assert.assertEquals(maxTemperature, wController.getTemperatureMaxFromCache(), 0.01);
    }

    
    @Test
    public void testTemperatureAverage() {
        double averageTemp = findAverageTemperature(hourlyTemperatures);
        Assert.assertEquals(averageTemp, wController.getTemperatureAverageFromCache(), 0.01);
    }

    private double findMinTemperature(double[] temperatures) {
        double minTemperature = Double.MAX_VALUE;
        for (double temperature : temperatures) {
            if (minTemperature > temperature) {
                minTemperature = temperature;
            }
        }
        return minTemperature;
    }

    private double findMaxTemperature(double[] temperatures) {
        double maxTemperature = -1;
        for (double temperature : temperatures) {
            if (maxTemperature < temperature) {
                maxTemperature = temperature;
            }
        }
        return maxTemperature;
    }

    private double findAverageTemperature(double[] temperatures) {
        double sumTemp = 0;
        for (double temperature : temperatures) {
            sumTemp += temperature;
        }
        return sumTemp / temperatures.length;
    }

    @Test
    public void testTemperaturePersist() {
        /*
         * Remove below comments ONLY for 5.3C task.
         */
//      String persistTime = wController.persistTemperature(10, 19.5);
//      Assert.assertEquals(persistTime, getCurrentTime());
    }

    // Helper method to get current time (for 5.3C task)
    // private String getCurrentTime() {
    //     return new SimpleDateFormat("H:m:s").format(new Date());
    // }
}