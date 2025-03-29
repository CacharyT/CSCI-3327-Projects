#Cachary Tolentino

#The function will return the salted data.
#Param: data - the data being salted
#Param: lowerBound - lower range of salting value
#Param: upperBound - higher range of salting value
#Return: newData - newly salted data
function [newData] = salterFunction(data, lowerBound, upperBound)
  #Declared Variables
  xValues = data(:, 1);
  yValues = data(:, 2);

  #Loop the entire data
  for i = 1:length(data)
    saltValue = lowerBound + randi(upperBound - lowerBound); #ensures different salt value per y value
    operation = randi([0,1]);

    if operation == 0 #Add
      yValues(i) += saltValue;
    else #subtract
      yValues(i) -= saltValue;
    endif
  endfor

  #Return the combined values together
  newData = [xValues, yValues];
  return;
endfunction
