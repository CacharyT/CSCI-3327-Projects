#Cachary Tolentino

#The function will handle the salting of the given data
#Param: fiileName - the name of the file containing the data
#Param: lowerBound - lower range of salting value
#Param: upperBound - higher range of salting value
#Return: new file name of the salted data
function [saltedName] = salter(fileName)
  #Declared Variables
  data = load([fileName, '.csv']);

  #Get user salting range
  disp("Please enter the following values (number; LowerBound < UpperBound)");
  lowerBound = input("Lower Bound: ");
  upperBound = input("Upper Bound: ");

  #salted data
  saltedData = salterFunction(data, lowerBound, upperBound);

  #Export the salted data as csv
  saltedName = [fileName, 'Salted'];
  exporter(saltedData, saltedName);
  return;
endfunction
