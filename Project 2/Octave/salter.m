#Cachary Tolentino

#The function will handle the salting of the given data
#Param: fileName - the name of the file containing the data
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

  #Export the salted data as csv and graph
  saltedName = [fileName, 'Salted'];
  exporter(saltedData, saltedName);
  grapher(saltedData, saltedName);
  return;
endfunction
