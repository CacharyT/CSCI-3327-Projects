#Cachary Tolentino

#The function handles user input for the quadratic function.
#Param: none
#Return: fileName: name of the file
function [fileName] = plotter()
  #Declared variables; seperated
  xValues = [];
  yValues = [];

  #Get user input
  disp("Please enter the following values (number)");
  trial = input("Trial Amount (start at 0 and end at trial amount for x): ");
  a = input("a: ");
  b = input("b: ");
  c = input("c: ");
  fileName = input("Name of file: ", 's');

  #Input Y values
  for x = 0:trial - 1
    y = quadraticFunction(x, a, b, c);
    xValues = [xValues, x];
    yValues = [yValues, y];
  endfor

  #Combine X and Y values, export as csv and graph
  values = [xValues', yValues'];
  exporter(values, fileName);
  grapher(values, fileName);
  return;
endfunction;
