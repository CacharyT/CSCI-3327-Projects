#Cachary Tolentino

#The function will write the given data into a csv fileName
#Param: data - the data being written
#Param: fileName - name of the file to be
#Return: none
function [] = exporter(data, fileName)
  csvwrite([fileName, '.csv'], data);
endfunction
