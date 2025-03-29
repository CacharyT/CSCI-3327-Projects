x = linspace(0, 10, 10);
sum = 0;
for val = x,
  sum = sum + val;
end;
printf('The sum from 0 to 10 is %i', sum);

total = 0;
while total <= 10
  disp(total);
  total += 1;
endwhile

newTotal = 0;
do
  disp(newTotal);
  newTotal += 2;
until(newTotal > 10);

z = 11;
if(z > 10)
  disp("Hello");
elseif(z == 10)
  disp("Hi");
else
  disp("Bye");
endif
