#basic instruction set for the mill.

##arithmetic
ADD <num1> <num2> <memory>     
SUB <num1> <num2> <memory>
MUL <num1> <num2> <memory>
DIV <num1> <num2> <memory>

##control
FORWARD <amount>
BACKWARD <amount>
HALT

##other
outputs the target memory's content to the targeted device.
OUTPUT <target address> <memory>

#information

Unlike the actual analitical engine, it uses hex codes, to work a bit better on a binary computer.