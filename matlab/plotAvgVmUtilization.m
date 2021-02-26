function [] = plotAvgVmUtilization(baseDir, baseFileName, withError, outputDir)

    plotGenericResult(2, 8, 'Average VM Utilization (%)', 'ALL_APPS', '', withError, baseDir, strcat(baseFileName, 'Average_VM_Utilization'), outputDir);
    
end