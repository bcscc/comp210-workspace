
package assn07;

import java.util.*;
import java.lang.Math;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password123";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }


    // TODO: put
    @Override
    public void put(K key, V value) {
        int index = Math.abs(key.hashCode() % 50);
        Account<K, V> account = new Account<>(key, value);
        if (_passwords[index] == null) {
            _passwords[index] = account;
        } else if (_passwords[index].getWebsite().equals(key)) {
            _passwords[index].setPassword(value);
        } else {
            Account<K, V> current = _passwords[index];
            while (!(current.getNext() == null)) {
                if (current.getNext().getWebsite().equals(key)) {
                    current.getNext().setPassword(value);
                    return;
                }
                current = current.getNext();
            }
            account.setNext(_passwords[index]);
            _passwords[index] = account;
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int index = Math.abs(key.hashCode() % 50);
        if (_passwords[index] == null) {
            return null;
        } else if (_passwords[index].getWebsite().equals(key)) {
            return (V)_passwords[index].getPassword();
        } else {
            Account<K, V> current = _passwords[index];
            while (!(current == null)) {
                if (current.getWebsite().equals(key)) {
                    return current.getPassword();
                }
                current = current.getNext();
            }
            return null;
        }
    }

    // TODO: size
    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < 50; i++) {
            Account <K, V> current = _passwords[i];
            if (!(current == null)) {
                size ++;
                while (!(current.getNext() == null)) {
                    size ++;
                    current = current.getNext();
                }
            }
        }
        return size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> accounts = new HashSet<K>();
        for (int i = 0; i < 50; i++) {
            Account <K, V> current = _passwords[i];
            if (!(current == null)) {
                accounts.add((K) current.getWebsite());
                while (!(current.getNext() == null)) {
                    current = current.getNext();
                    accounts.add((K)current.getWebsite());
                }
            }
        }
        return accounts;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int index = Math.abs(key.hashCode() % 50);
        if (_passwords[index] == null) {
            return null;
        } else {
            Account<K, V> current = _passwords[index];
            Account<K, V> previous = null;
            while (!(current.getNext() == null) && !(current.getWebsite().equals(key))) {
                previous = current;
                current = current.getNext();
            }
            V pass = current.getPassword();
            if (current.getWebsite().equals(key)) {
                if (previous == null) {
                    _passwords[index] = current.getNext();
                }
                else {
                    previous.setNext(current.getNext());
                }
            }
            return pass;
        }
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> accounts = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (!(_passwords[i] == null)) {
                if(_passwords[i].getPassword().equals(value)) {
                    accounts.add((K)_passwords[i].getWebsite());
                }
                Account<K, V> current = _passwords[i];
                while (!(current.getNext() == null)) {
                    if(current.getNext().getPassword().equals(value)) {
                        accounts.add((K)current.getNext().getWebsite());
                    }
                    current = current.getNext();
                }
            }
        }
        return accounts;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        if (enteredPassword.equals(MASTER_PASSWORD)) {
            return true;
        } else {
            return false;
        }
    }

    /*
    Generates random password of input length
     */
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}