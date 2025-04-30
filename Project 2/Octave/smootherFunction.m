#Cachary Tolentino

#The function will smoothen the given salted data
#Param: data - the data containing the salted values
#Param: windowValue - the number of values to average around
#Return: newData - the smoothened data
function [newData] = smootherFunction(data, windowValue)
  #Declared Variables
  xValues = data(:, 1);
  yValues = data(:, 2);

  #New Y values
  newYValues = zeros(size(yValues));

  #Loop the entire data
  for i = 1:length(data)
    left = floor((windowValue - 1) / 2); #left side to count
    right = ceil((windowValue - 1) / 2); #right side to count

    startVal = max(1, i - left); #left most value position; can't go lower than 0
    endVal = min(length(yValues), i + right); #right most value position; can't go beyond size of data

    #Replace current value with the average of the windowValue
    newYValues(i) = mean(yValues(startVal:endVal));
  endfor

  #Return the combined values together
  newData = [xValues, newYValues];
  return;
endfunction
