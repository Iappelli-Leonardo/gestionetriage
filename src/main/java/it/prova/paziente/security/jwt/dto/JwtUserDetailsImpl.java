package it.prova.paziente.security.jwt.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import it.prova.paziente.model.StatoUtente;
import it.prova.paziente.model.User;


public class JwtUserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final String username;
	private final String password;
	private final String nome;
	private final String cognome;
	private final Date dataCreazione;
	private final StatoUtente stato;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;

	public JwtUserDetailsImpl(String username, String password, String nome, String cognome, Date dataCreazione, StatoUtente stato, Collection<? extends GrantedAuthority> authorities,
			boolean enabled) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome=cognome;
		this.dataCreazione = dataCreazione;
		this.stato = stato;
		this.authorities = authorities;
		this.enabled = enabled;
	}
	
	public static JwtUserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
		
        return new JwtUserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                user.getNome(),
                user.getCognome(),
                user.getDataCreazione(),
                user.getStato(),
                authorities,
                user.getEnabled()
        );
    }

	@Override
	public String getUsername() {
		return username;
	}


	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}
	
	public Date getDataCreazione() {
		return dataCreazione;
	}
	
	public StatoUtente getStato() {
		return stato;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		JwtUserDetailsImpl user = (JwtUserDetailsImpl) o;
		return Objects.equals(username, user.username);
	}

}