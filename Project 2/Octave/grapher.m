#Cachary Tolentino

#The function will graph and save the current figure
#Param: data - the data being written
#Param: fileName - name of the file to be
#Return: none
function [] = grapher(data, fileName)
  #Declared Variables
  xValues = data(:, 1);
  yValues = data(:, 2);

  #Display the graph
  plot(xValues, yValues);
  title(fileName);
  xlabel("X-Values");
  ylabel("Y-Values");
  grid on;

  #save graph as an image
  saveas(gcf, [fileName, " Graph.png"]); #current figure will be saved
endfunction
