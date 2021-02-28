function [] = plotAll()
	addpath(fullfile('./', 'matlab'));
	initEnv();
	mkdir('./output/charts/behaviourAndCommunication');
	mkdir('./output/charts/behaviourWithCommunication');
	mkdir('./output/charts/onlyCommunication');
	for i=0:24
		plotResults('./output/results/behaviourAndCommunication', i, strcat('EaC', int2str(i), '_'), false, './output/charts/behaviourAndCommunication');
		plotResults('./output/results/behaviourAndCommunication', i, strcat('EaC', int2str(i), '_withStdv_'), true, './output/charts/behaviourAndCommunication');
	end
	for i=0:4
		plotResults('./output/results/behaviourWithCommunication', i, strcat('EwC', int2str(i), '_'), false, './output/charts/behaviourWithCommunication');
		plotResults('./output/results/behaviourWithCommunication', i, strcat('EwC', int2str(i), '_withStdv_'), true, './output/charts/behaviourWithCommunication');
	end
	for i=0:4
		plotResults('./output/results/onlyCommunication', i, strcat('C', int2str(i), '_'), false, './output/charts/onlyCommunication');
		plotResults('./output/results/onlyCommunication', i, strcat('C', int2str(i), '_withStdv_'), true, './output/charts/onlyCommunication');
	end
end