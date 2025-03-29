#The function returns the quadratic value
#Param: x - the x value
#Param: a - the a value
#Param: b - the b value
#Param: c - the c value
#Return: y - the quadratic value
function [y] = quadraticFunction(x, a, b, c)
  y = (a*(x.^2)) + (b*x) + c;
  return;
endfunction
