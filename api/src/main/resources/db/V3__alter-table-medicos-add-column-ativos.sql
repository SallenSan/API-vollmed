alter table medicos add column ativos tinyint;
update medicos set ativos=1;