function initEnv

installlist = {'io','statistics'};
for ii=1:length(installlist)
        try
        	% Try loading Octave packages
            disp(['loading ' installlist{ii}])
            pkg('load',installlist{ii})
        
        catch
            errorcount = 1;
            while errorcount % Attempt twice in case installation fails
                try
                    pkg('install','-forge',installlist{ii})
                    pkg('load',installlist{ii})
                    errorcount = 0;
                catch err
                    errorcount = errorcount+1;
                    if errorcount>2
                        error(err.message)
                    end
                end
            end
        end
end