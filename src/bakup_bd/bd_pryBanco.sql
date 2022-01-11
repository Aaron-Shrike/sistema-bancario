CREATE TABLE public.cliente
(
    dni_cliente character(8) NOT NULL,
    nombre_cliente character varying(30) NOT NULL,
    apellido_paterno_cliente character varying(15) NOT NULL,
    apellido_materno_cliente character varying(15) NOT NULL,
    fecha_nacimiento_cliente date NOT NULL,
    direccion_cliente character varying(50) NOT NULL,
    telefono_cliente character(9) NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (dni_cliente)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.cliente
    OWNER to postgres;



CREATE TABLE public.cuenta
(
    numero_cuenta character(20) NOT NULL,
    fecha_apertura_cuenta date NOT NULL,
    monto_inicial_cuenta double precision NOT NULL,
    tipo_moneda_cuenta character varying(10) NOT NULL,
    estado_cuenta character varying(10) NOT NULL,
    dni_cliente_cuenta character(8) NOT NULL,
    CONSTRAINT pk_cuenta PRIMARY KEY (numero_cuenta)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.cuenta
    OWNER to postgres;

insert into cliente
(dni_cliente, nombre_cliente, apellido_paterno_cliente, 
 apellido_materno_cliente, fecha_nacimiento_cliente,
 direccion_cliente, telefono_cliente)
 values
 ('73976770', 'Aaron', 'Rojas', 'Vera', '2000-12-23',
  'Prol. Garcilazo de la Vega Cdra. 1 Mz. H Lt. 05', '978488529')
  
 insert into cliente
(dni_cliente, nombre_cliente, apellido_paterno_cliente, 
 apellido_materno_cliente, fecha_nacimiento_cliente,
 direccion_cliente, telefono_cliente)
 values
 ('12345678', 'Alejandra', 'Garcia', 'Lopez', '1999-10-15',
  'Av. Asd 1234', '948840373')
  
  insert into cliente
(dni_cliente, nombre_cliente, apellido_paterno_cliente, 
 apellido_materno_cliente, fecha_nacimiento_cliente,
 direccion_cliente, telefono_cliente)
 values
 ('87654321', 'Melyssa', 'Farroñan', '--', current_date,
  'Call. Asd 1234', '982860640')
  
  insert into cliente
(dni_cliente, nombre_cliente, apellido_paterno_cliente, 
 apellido_materno_cliente, fecha_nacimiento_cliente,
 direccion_cliente, telefono_cliente)
 values
 ('12348765', 'Rafael', 'Ramirez', 'Benites', '1999-07-13',
  'Km. Asd 1234', '959417326')


insert into cuenta
(numero_cuenta, fecha_apertura_cuenta, monto_inicial_cuenta, 
 tipo_moneda_cuenta, estado_cuenta, dni_cliente_cuenta)
 values
 ('12345678912345678912', current_date, 400.0, 'SOLES',
  'ABIERTA', '73976770')
  
  insert into cuenta
(numero_cuenta, fecha_apertura_cuenta, monto_inicial_cuenta, 
 tipo_moneda_cuenta, estado_cuenta, dni_cliente_cuenta)
 values
 ('43215678912345678912', current_date, 1000.0, 'SOLES',
  'ABIERTA', '12345678')
  
  insert into cuenta
(numero_cuenta, fecha_apertura_cuenta, monto_inicial_cuenta, 
 tipo_moneda_cuenta, estado_cuenta, dni_cliente_cuenta)
 values
 ('12435678912345678912', current_date, 2200.0, 'DOLARES',
  'ABIERTA', '87654321')
  
  insert into cuenta
(numero_cuenta, fecha_apertura_cuenta, monto_inicial_cuenta, 
 tipo_moneda_cuenta, estado_cuenta, dni_cliente_cuenta)
 values
 ('21345678912345678912', current_date, 3000.0, 'EUROS',
  'ABIERTA', '12348765')

  
  update cliente set fecha_nacimiento_cliente = '1999-07-13'
  where dni_cliente = '12348765'

select extract (year from fecha_apertura_cuenta) from cuenta
  
select * from cuenta where extract(year from fecha_apertura_cuenta) = 2020

 select tipo_moneda_cuenta, count(*) as cantidad from cuenta group by tipo_moneda_cuenta