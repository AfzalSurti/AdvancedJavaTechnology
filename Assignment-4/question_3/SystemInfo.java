import java.io.Serializable;

public class SystemInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    public String osName;
    public String osVersion;
    public String osArch;

    public long totalDiskBytes;
    public long freeDiskBytes;

    public long totalRamBytes;
    public long usedRamBytes;

    public static String humanBytes(long bytes) {
        double b = bytes;
        String[] units = {"B", "KB", "MB", "GB", "TB"};
        int i = 0;
        while (b >= 1024 && i < units.length - 1) {
            b /= 1024;
            i++;
        }
        return String.format("%.2f %s", b, units[i]);
    }

    @Override
    public String toString() {
        return "Remote System Info ✅\n"
            + "OS Name: " + osName + "\n"
            + "OS Version: " + osVersion + "\n"
            + "OS Arch: " + osArch + "\n\n"
            + "Disk Total: " + humanBytes(totalDiskBytes) + "\n"
            + "Disk Available: " + humanBytes(freeDiskBytes) + "\n\n"
            + "RAM Total: " + humanBytes(totalRamBytes) + "\n"
            + "RAM Used: " + humanBytes(usedRamBytes) + "\n";
    }
}