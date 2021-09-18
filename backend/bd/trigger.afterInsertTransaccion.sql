CREATE DEFINER=`root`@`localhost` TRIGGER `transacciones_AFTER_INSERT` AFTER INSERT ON `transacciones` FOR EACH ROW BEGIN
    IF new.tipo = 1 THEN
		update usuarios us
		set us.saldo = (us.saldo + NEW.monto)
		where us.rut = NEW.rut;
	ELSEIF new.tipo = 0 THEN 
    	update usuarios us
		set us.saldo = (us.saldo - NEW.monto)
		where us.rut = NEW.rut;
    END IF;
END