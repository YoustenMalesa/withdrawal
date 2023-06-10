CREATE TABLE IF NOT EXISTS public.address
(
    id bigint NOT NULL DEFAULT nextval('address_id_seq'::regclass),
    city character varying(255) COLLATE pg_catalog."default",
    date_created timestamp(6) without time zone,
    date_updated timestamp(6) without time zone,
    street_details character varying(255) COLLATE pg_catalog."default",
    suburb character varying(255) COLLATE pg_catalog."default",
    zip_code bigint,
    CONSTRAINT address_pkey PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS public.contact
(
    id bigint NOT NULL DEFAULT nextval('contact_id_seq'::regclass),
    date_created timestamp(6) without time zone,
    date_updated timestamp(6) without time zone,
    email character varying(255) COLLATE pg_catalog."default",
    mobile_number character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT contact_pkey PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS public.investor
(
    id bigint NOT NULL DEFAULT nextval('investor_id_seq'::regclass),
    date_created timestamp(6) without time zone,
    date_of_birth date,
    date_updated timestamp(6) without time zone,
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    address_id bigint,
    contact_id bigint,
    CONSTRAINT investor_pkey PRIMARY KEY (id),
    CONSTRAINT uk_56wicndj8smg2upjl2f7apuys UNIQUE (contact_id),
    CONSTRAINT uk_i19m0xboubehq4ruqwc09htih UNIQUE (address_id),
    CONSTRAINT fk8aq9nl18jw3ndlearekyagiex FOREIGN KEY (contact_id)
    REFERENCES public.contact (id) MATCH SIMPLE
                              ON UPDATE NO ACTION
                              ON DELETE NO ACTION,
    CONSTRAINT fkrga81xovaj520possfs4q4p5f FOREIGN KEY (address_id)
    REFERENCES public.address (id) MATCH SIMPLE
                              ON UPDATE NO ACTION
                              ON DELETE NO ACTION
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS public.investor_product
(
    investor_id bigint NOT NULL,
    product_id bigint NOT NULL,
    CONSTRAINT investor_product_pkey PRIMARY KEY (investor_id, product_id),
    CONSTRAINT fk8rows4fe0aik5y6o71k031usm FOREIGN KEY (investor_id)
    REFERENCES public.investor (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkr6wo7haoarfmigj2g277gqovf FOREIGN KEY (product_id)
    REFERENCES public.product (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS public.product
(
    id bigint NOT NULL DEFAULT nextval('product_id_seq'::regclass),
    current_balance double precision,
    name character varying(255) COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    investor_id bigint,
    date_created timestamp(6) without time zone,
    date_updated timestamp(6) without time zone,
    CONSTRAINT product_pkey PRIMARY KEY (id),
    CONSTRAINT fk38ttt82y6eg0ffnr1ngwxq47a FOREIGN KEY (investor_id)
    REFERENCES public.investor (id) MATCH SIMPLE
                              ON UPDATE NO ACTION
                              ON DELETE NO ACTION
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS public.withdrawal
(
    id bigint NOT NULL DEFAULT nextval('withdrawal_id_seq'::regclass),
    amount double precision,
    date_created date,
    date_updated date,
    investor_id bigint,
    product_id bigint,
    status character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT withdrawal_pkey PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
