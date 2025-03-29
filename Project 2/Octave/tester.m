#Cachary Tolentino

#The file tests the scripts which emulates the PSS program
fileName = plotter();
saltedName = salter(fileName, 1000, 5000);
smoother(saltedName, 10);
