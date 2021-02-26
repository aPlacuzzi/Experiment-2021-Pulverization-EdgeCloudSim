function [] = plotAvgVmUtilization(baseDir, folderNum, baseFileName, withError, outputDir)

    plotGenericResult(2, 8, 'Average VM Utilization (%)', 'ALL_APPS', '', withError, baseDir, folderNum, strcat(baseFileName, 'Average_VM_Utilization'), outputDir);
    
end