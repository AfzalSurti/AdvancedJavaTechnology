import java.io.File;
import java.lang.management.ManagementFactory;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SystemInfoServiceImpl extends UnicastRemoteObject implements SystemInfoService {
    private static final long serialVersionUID = 1L;

    protected SystemInfoServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public SystemInfo getSystemInfo() throws RemoteException {
        SystemInfo info = new SystemInfo();

        // OS info
        info.osName = System.getProperty("os.name");
        info.osVersion = System.getProperty("os.version");
        info.osArch = System.getProperty("os.arch");

        // Disk info (sum all drives)
        long totalDisk = 0;
        long freeDisk = 0;
        File[] roots = File.listRoots();
        if (roots != null) {
            for (File r : roots) {
                totalDisk += r.getTotalSpace();
                freeDisk += r.getUsableSpace();
            }
        }
        info.totalDiskBytes = totalDisk;
        info.freeDiskBytes = freeDisk;

        // RAM info (physical memory)
        try {
            com.sun.management.OperatingSystemMXBean osBean =
                (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

            long totalRam = osBean.getTotalPhysicalMemorySize();
            long freeRam = osBean.getFreePhysicalMemorySize();
            info.totalRamBytes = totalRam;
            info.usedRamBytes = totalRam - freeRam;

        } catch (Exception e) {
            // Fallback: JVM heap if physical RAM is not accessible
            Runtime rt = Runtime.getRuntime();
            long totalHeap = rt.totalMemory();
            long freeHeap = rt.freeMemory();
            info.totalRamBytes = totalHeap;
            info.usedRamBytes = totalHeap - freeHeap;
        }

        return info;
    }
}