#Cachary Tolentino

#The function will handle smoothing the salted data
#Param: fileName - name of the file containing the salted data
#Param: windowValue - the number of values to average around
#Return: none
function [] = smoother(fileName, windowValue)
 #Declared Variables
  data = load([fileName, '.csv']);

  #smoothened data
  smoothenedData = smootherFunction(data, windowValue);

  #Export the salted data as csv
  smoothenedName = [fileName, 'Smoothened'];
  exporter(smoothenedData, smoothenedName);
endfunction

