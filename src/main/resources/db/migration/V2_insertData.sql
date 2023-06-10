INSERT INTO public.address(
    id, city, date_created, date_updated, street_details, suburb, zip_code)
VALUES (100, 'Birchleigh', null, null, '4093 Tamboti Avenue', 'Birch Acres', 1157);

INSERT INTO public.contact(
    id, date_created, date_updated, email, mobile_number)
VALUES (111, null, null, 'thutomalesa@gmail.com', '0840351666');

INSERT INTO public.investor(
    id, date_created, date_of_birth, date_updated, first_name, last_name, address_id, contact_id)
VALUES (101, null, '1942-08-07', null, 'Thuto', 'Malesa', 100, 111);

INSERT INTO public.product(
    id, current_balance, name, type, investor_id, date_created, date_updated)
VALUES (112, 500000, 'Pension Fund', 'RETIREMENT', 101, null, null);

INSERT INTO public.product(
    id, current_balance, name, type, investor_id, date_created, date_updated)
VALUES (113, 36000, 'Savings Investment', 'SAVINGS', 101, null, null);


INSERT INTO public.investor_product(
    investor_id, product_id)
VALUES (101, 112);

INSERT INTO public.investor_product(
    investor_id, product_id)
VALUES (101, 113);