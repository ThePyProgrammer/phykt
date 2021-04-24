package com.thepyprogrammer.phykt.unit

val cm = Unit("m", "centimetre", "length", 0.01)
val cs = Unit("s", "centisecond", "time", 0.01, isScalar=true)
val cg = Unit("g", "centigram", "mass", 0.01/1000, isScalar=true)
val cA = Unit("A", "centiampere", "electric current", 0.01, isScalar=true)
val cK = Unit("K", "centikelvin", "thermodynamic temperature", 0.01, isScalar=true)
val cmol = Unit("mol", "centimole", "amount of substance", 0.01, isScalar=true)
val ccd = Unit("cd", "centicandela", "luminous intensity", 0.01, isScalar=true)
val cHz = Unit("1.0/s", "centihertz", "frequency", 0.01, isScalar=true)
val cN = Unit("gm/s^2", "centinewton", "force", 0.01)
val cPa = Unit("g/ms^2", "centipascal", "pressure, stress", 0.01, isScalar=true)
val cJ = Unit("gm^2/s^2", "centijoule", "energy, work, amount of heat", 0.01, isScalar=true)
val cW = Unit("gm^2/s^3", "centiwatt", "power, radiant flux", 0.01, isScalar=true)
val cC = Unit("As", "centicoulomb", "electric charge, quantity of electricity", 0.01, isScalar=true)
val cV = Unit("gm^2/As^3", "centivolt", "electric potential, emf", 0.01, isScalar=true)
val cΩ = Unit("gm^2/s^3A^2", "centiohm", "electric resistance, impedance", 0.01, isScalar=true)
val cohm = Unit("gm^2/s^3A^2", "centiohm", "electric resistance, impedance", 0.01, isScalar=true)
val ckat = Unit("mol/s", "centikatal", "catalytic activity", 0.01, isScalar=true)
val cS = Unit("A^2s^3/gm^2", "centisiemen", "electric conductance", 0.01, isScalar=true)
val cF = Unit("A^2s^4/gm^2", "centifarad", "capacitance", 0.01, isScalar=true)
val cWb = Unit("gm^2/As^2", "centiweber", "magnetic flux", 0.01, isScalar=true)
val cT = Unit("g/As^2", "centitesla", "magnetic field", 0.01)
val cH = Unit("gm^2/A^2s^2", "centihenry", "inductance", 0.01, isScalar=true)
val cdegreeCelsius = Unit("K", "centi°Celsius", "temperature on the celsius scale", 0.01, -273.15, isScalar=true)
val cdegCelsius = Unit("K", "centi°Celsius", "temperature on the celsius scale", 0.01, -273.15, isScalar=true)
val cdegC = Unit("K", "centi°Celsius", "temperature on the celsius scale", 0.01, -273.15, isScalar=true)
val cGy = Unit("m^2/s^2", "centigray", "absorbed dose, specific energy", 0.01, isScalar=true)
val cSv = Unit("m^2/s^2", "centisievert", "dose equivalent(d)", 0.01, isScalar=true)
val cBq = Unit("1.0/s", "centibecquerel", "activity (of a radionuclide)", 0.01, isScalar=true)
val clm = Unit("cd", "centilumen", "luminous flux", 0.01, isScalar=true)
val clx = Unit("cd/m^2", "centilux", "illuminance", 0.01, isScalar=true)
val ceV = Unit("gm^2/s^2", "centielectron Volts", "measure of energy", 0.01*1.60217648740e-19, isScalar=true)
val cly = Unit("m", "centilightyear", "measure of distance", 0.01*3600*24*365.25*299792458)
val cpc = Unit("m", "centiparsec", "measure of distance", 0.01*3.086e16, isScalar=true)
val cL = Unit("m^3", "centilitre", "measure of volume", 0.01/1000, isScalar=true)