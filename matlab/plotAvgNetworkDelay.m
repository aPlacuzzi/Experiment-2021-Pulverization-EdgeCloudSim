function [] = plotAvgNetworkDelay(baseDir, folderNum, baseFileName, withError, outputDir)

    plotGenericResult(1, 7, 'Average Network Delay (sec)', 'ALL_APPS', '', withError, baseDir, folderNum, strcat(baseFileName, 'Average_Network_Delay'), outputDir);
    
%    plotGenericResult(5, 1, 'Average WLAN Delay (sec)', 'ALL_APPS', '', withError, baseDir, folderNum, strcat(baseFileName, 'Average_WLAN_Delay'), outputDir);
    
%    plotGenericResult(5, 3, 'Average WAN Delay (sec)', 'ALL_APPS', '', withError, baseDir, folderNum, strcat(baseFileName, 'Average_WAN_Delay'), outputDir);
    
end