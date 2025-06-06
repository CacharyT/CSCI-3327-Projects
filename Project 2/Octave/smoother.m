#Cachary Tolentino

#The function will handle smoothing the salted data
#Param: fileName - name of the file containing the salted data
#Return: none
function [] = smoother(fileName)
 #Declared Variables
  data = load([fileName, '.csv']);

  #Get user salting range
  disp("Please enter the following values (number)");
  windowValue = input("Window Value: ");

  #smoothened data
  smoothenedData = smootherFunction(data, windowValue);

  #Export the salted data as csv and graph
  smoothenedName = [fileName, 'Smoothened'];
  exporter(smoothenedData, smoothenedName);
  grapher(smoothenedData, smoothenedName);
endfunction

