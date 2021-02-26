function [all_results] = loadData(rowOfset, columnOfset, appType, calculatePercentage, folderPath, folderNum)
	 numOfSimulations = getConfiguration(2);
    startOfMobileDeviceLoop = getConfiguration(3);
    stepOfMobileDeviceLoop = getConfiguration(4);
    endOfMobileDeviceLoop = getConfiguration(5);
    
    scenarioType = getConfiguration(7);
    numOfMobileDevices = (endOfMobileDeviceLoop - startOfMobileDeviceLoop)/stepOfMobileDeviceLoop + 1;
	
	  all_results = zeros(numOfSimulations, size(scenarioType,2), numOfMobileDevices);
	
    for s=1:numOfSimulations
        for j=1:numOfMobileDevices
          try
              mobileDeviceNumber = startOfMobileDeviceLoop + stepOfMobileDeviceLoop * (j-1);
              filePath = strcat(folderPath, '0','\ite', int2str(s),'\SIMRESULT_',char(scenarioType(1)),'_NEXT_FIT_',int2str(mobileDeviceNumber),'DEVICES_',appType,'_GENERIC.log');

              readData = dlmread(filePath,';',rowOfset,0);
              value = readData(1,columnOfset);
              if(strcmp(calculatePercentage,'percentage_for_all'))
                  readData = dlmread(filePath,';',1,0);
              totalTask = readData(1,1)+readData(1,2);
                  value = (100 * value) / totalTask;
              elseif(strcmp(calculatePercentage,'percentage_for_completed'))
                  readData = dlmread(filePath,';',1,0);
              totalTask = readData(1,1);
                  value = (100 * value) / totalTask;
              elseif(strcmp(calculatePercentage,'percentage_for_failed'))
                  readData = dlmread(filePath,';',1,0);
              totalTask = readData(1,2);
                  value = (100 * value) / totalTask;
              end

              all_results(s, 1, j) = value;
          catch err
              error(err)
          end
      end   
      for i=2:size(scenarioType,2)
        for j=1:numOfMobileDevices
          try
              mobileDeviceNumber = startOfMobileDeviceLoop + stepOfMobileDeviceLoop * (j-1);
              filePath = strcat(folderPath, int2str(folderNum),'\ite', int2str(s),'\SIMRESULT_',char(scenarioType(i)),'_NEXT_FIT_',int2str(mobileDeviceNumber),'DEVICES_',appType,'_GENERIC.log');

              readData = dlmread(filePath,';',rowOfset,0);
              value = readData(1,columnOfset);
              if(strcmp(calculatePercentage,'percentage_for_all'))
                  readData = dlmread(filePath,';',1,0);
              totalTask = readData(1,1)+readData(1,2);
                  value = (100 * value) / totalTask;
              elseif(strcmp(calculatePercentage,'percentage_for_completed'))
                  readData = dlmread(filePath,';',1,0);
              totalTask = readData(1,1);
                  value = (100 * value) / totalTask;
              elseif(strcmp(calculatePercentage,'percentage_for_failed'))
                  readData = dlmread(filePath,';',1,0);
              totalTask = readData(1,2);
                  value = (100 * value) / totalTask;
              end
              all_results(s, i, j) = value;
          catch err
              error(err)
          end
      end             
      end
    end
end