package pl.org.grzybek.tieto.storage;

/**
 * Interface representing key-value storage
 * 
 * @author zgrzybek
 * 
 */
public interface KeyValueStorage<K, V> {

	/**
	 * Save new or updates existing value in the storage
	 * 
	 * @param key
	 * @param value
	 * @return latest value
	 */
	public V saveOrUpdate(K key, V value);

	/**
	 * Retrieves value from the storage
	 * 
	 * @param cityName
	 * @return
	 */
	public V get(K key);
}