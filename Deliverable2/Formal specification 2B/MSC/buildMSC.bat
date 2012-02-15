@echo off
for %%x in (*.def) do mscgen -T png -i %%x
pause
exit
