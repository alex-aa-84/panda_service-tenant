package wwf.org.tenant.service;

import wwf.org.tenant.entity.Country;

import java.util.List;

public interface CountryService {
    public List<Country> listAllCountry();
    public Country getCountry(Long id);
    public Country createCountry(Country country);
    public Country updateCountry(Country country);
    public Country deleteCountry(Country country);

}
