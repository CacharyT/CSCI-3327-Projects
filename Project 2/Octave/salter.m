#Cachary Tolentino

#The function will handle the salting of the given data
#Param: fiileName - the name of the file containing the data
#Param: lowerBound - lower range of salting value
#Param: upperBound - higher range of salting value
#Return: new file name of the salted data
function [saltedName] = salter(fileName, lowerBound, upperBound)
  #Declared Variables
  data = load([fileName, '.csv']);

  #salted data
  saltedData = salterFunction(data, lowerBound, upperBound);

  #Export the salted data as csv
  saltedName = [fileName, 'Salted'];
  exporter(saltedData, saltedName);
  return;
endfunction
