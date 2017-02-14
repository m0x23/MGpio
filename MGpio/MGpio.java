package MGpio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * GPIO control class
 *
 * @author MarkB - m0x23
 * @version 0.1
 */
public class MGpio
{
    private String gpioPort;
    private String gpioDirection;
    private String gpioValue;
    private String gpioFolderName;
    private String gpioDirectionPath;
    private String gpioValuePath;

    /**
     * Exports the given port to Raspberry Pi GPIO board
     * Direction must be set after object init setGpioDirection(String)
     *
     * @param port port number
     */
    MGpio(String port)
    {
        this.gpioPort = port;
        this.gpioFolderName = "gpio" + this.gpioPort;
        this.gpioDirectionPath = "/sys/class/gpio/" + gpioFolderName + "/direction";
        this.gpioValuePath = "/sys/class/gpio/" + gpioFolderName + "/value";

        FileWriter gpioExport;
        FileWriter gpioUnexport;

        try
        {
            gpioExport = new FileWriter("/sys/class/gpio/export");
            gpioUnexport = new FileWriter("/sys/class/gpio/unexport");

            File exportFileCheck = new File("/sys/class/gpio/gpio" + this.gpioPort);
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
            System.out.println("export/unexport file not found");
        }
    }

    /**
     * Sets a new gpio value
     *
     * @param val gpio value
     */
    public void setGpioValue(String val)
    {
        try
        {
            FileWriter fw;
            fw = new FileWriter(gpioValuePath);
            this.gpioValue = val;
            fw.write(this.gpioValue);
            fw.close();
        }
        catch (IOException ex)
        {
            System.out.println("setGpioValue(): file not found:");
            System.out.println(this.gpioValuePath);
        }
    }

    /**
     * Sets a new gpio direction
     *
     * @param direc gpio direction
     */
    public void setGpioDirection(String direc)
    {
        try
        {
            FileWriter gpioDirectionWriter;
            gpioDirectionWriter = new FileWriter(this.gpioDirectionPath);
            this.gpioDirection = direc;
            gpioDirectionWriter.write(this.gpioDirection);
            gpioDirectionWriter.close();
        }
        catch (IOException ex)
        {
            System.out.println("setGpioDirection(): file not found: ");
            System.out.println(this.gpioDirectionPath);
        }
    }

    /**
     * @return gpio port
     */
    public String getGpioPort()
    {
        return this.gpioPort;
    }

    /**
     * Gets the current gpio direction
     *
     * @return current direction
     */
    public String getGpioDirection()
    {
        return this.gpioDirection;
    }

    /**
     * Gets the current gpio value
     *
     * @return current value
     */
    public String getGpioValue()
    {
        return this.gpioValue;
    }

    /**
     * @return gpio folder name
     */
    public String getGpioFolderName()
    {
        return this.gpioFolderName;
    }

    /**
     * @return gpio direction path
     */
    public String getGpioDirectionPath()
    {
        return this.gpioDirectionPath;
    }

    /**
     * @return gpio value path
     */
    public String getGpioValuePath()
    {
        return this.gpioValuePath;
    }
}
