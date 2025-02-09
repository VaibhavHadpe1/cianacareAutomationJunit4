package basePackage;

import java.io.IOException;

public class EmulatorLauncher {

    public static void main(String[] args) {
        startEmulator();
    }
    public static void startEmulator()
    {
        // Full path to the emulator binary
        String emulatorPath = "C:\\Users\\Archents\\AppData\\Local\\Android\\Sdk\\emulator\\emulator";

        // The AVD name you want to launch
        String avdName = "Pixel_7_API_33";

        // Construct the command to launch the emulator
        String[] emulatorCommand = { emulatorPath, "-avd", avdName };

        ProcessBuilder processBuilder = new ProcessBuilder(emulatorCommand);
        try
        {
            // Start the process
            Thread.sleep(5000);
            Process process = processBuilder.start();
            System.out.println("Emulator started");
            Thread.sleep(2000);
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
