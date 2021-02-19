function [] = plotAvgVmUtilization(baseDir, baseFileName)

    plotGenericResult(2, 8, 'Average VM Utilization (%)', 'ALL_APPS', '', baseDir, strcat(baseFileName, 'Average_VM_Utilization'));
    
end