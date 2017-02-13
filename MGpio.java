package MGpio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * GPIO control class
 * @author markb
 * @version 0.1
 */
public class MGpio {

    String gpioName;
    String gpioDirectionPath;
    String gpioValuePath;
    String gpioDirection;
    String gpioPort;

    /**
     * Exports the given port to Raspberry Pi
     * Direction must be set after object init setGpioDirection(String)
     * @param str port number
     */
    MGpio(String str) 
    {
        this.gpioPort = str;
        this.gpioName = "gpio" + this.gpioPort;
        this.gpioDirectionPath = "/sys/class/gpio/" + gpioName + "/direction";
        this.gpioValuePath = "/sys/class/gpio/" + gpioName + "/value";
        this.gpioDirection = "out"; //default direction value

        FileWriter gpioExport;
        FileWriter gpioUnexport;
        
        try 
        {
            gpioExport = new FileWriter("/sys/class/gpio/export");
            gpioUnexport = new FileWriter("/sys/class/gpio/unexport");
            
            File exportFileCheck = new File("/sys/class/gpio/gpio"+this.gpioPort);
            if (exportFileCheck.exists()) 
            {
                gpioUnexport.write(gpioPort);
                gpioUnexport.flush();
                gpioUnexport.close();
            }
            gpioExport.write(this.gpioPort);
            gpioExport.close();
        } 
        catch (IOException ex) 
        {
            System.out.println("Export/Unexport Datei nicht gefunden");
        }
    }

    /**
     * Sets a new gpio value
     * @param str gpio value
     */
    public void setGpioValue(String str) {
        try {
            FileWriter fw = new FileWriter(gpioValuePath);
            fw.write(str);
            fw.close();
        } 
        catch (IOException ex) 
        {
            System.out.println("setGpioValue(): Datei nicht gefunden:");
            System.out.println(this.gpioValuePath);
        }
    }
    /**
     * Sets a new gpio direction
y        * @param str New gpio direction
     */
    public void setGpioDirection(String str)
    {
        try 
        {
            this.gpioDirection = str;
            FileWriter gpioDirectionWriter;
            gpioDirectionWriter = new FileWriter(this.gpioDirectionPath);
            gpioDirectionWriter.write(this.gpioDirection);
            gpioDirectionWriter.close();
        } 
        catch (IOException ex) 
        {
            System.out.println("setGpioDirection(): Datei nicht gefunden: ");
            System.out.println(this.gpioDirectionPath);
        }
    }
    
    /**
     * Gets the current gpio value
     * Returns String "-1" when failed
     * @return Current value
     */
    public String getGpioValue()
    {
        File f = new File(this.gpioValuePath);
        InputStream is = null;
        try {
            
            is = new FileInputStream(f);
            Scanner scanner = new Scanner(is, "UTF-8");
            while (scanner.hasNext()) 
                {
                        String line = scanner.nextLine();
                        return line;
                }
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("getGpioValue(): Datei nicht gefunden:");
            System.out.println(this.gpioValuePath);
        }
        return "-1";
    }
    
    /**
     * Gets the current gpio direction
     * Returns String "-1" when failed
     * @return Current direction
     */
    public String getGpioDirection()
    {
        File f = new File(this.gpioDirectionPath);
        InputStream is = null;
        try {
            
            is = new FileInputStream(f);
            Scanner scanner = new Scanner(is, "UTF-8");
            while (scanner.hasNext()) 
                {
                    String line = scanner.nextLine();
                    return line;
                }
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("getGpioDirection(): Datei nicht gefunden");
            System.out.println(this.gpioDirectionPath);
        }
        return "-1";
    }
    
    public String getGpioPort()
    {
        return this.gpioPort;
    }
    
    public String getGpioName()
    {
        return this.gpioName;
    }
    
    
    
    
}
