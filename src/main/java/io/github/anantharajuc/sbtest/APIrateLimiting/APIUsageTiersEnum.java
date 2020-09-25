package io.github.anantharajuc.sbtest.APIrateLimiting;

import java.time.Duration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import lombok.AllArgsConstructor;

/**
 * Enum that classifies the usage tiers in the API.
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@AllArgsConstructor
public enum APIUsageTiersEnum 
{
	FREE(3),
    BASIC(4),
    PROFESSIONAL(5);
	
	private int bucketCapacity;
	
	/**
     * Method to get the Bandwidth, that is composed by the bucket capacity and the refill interval.
     * 
     * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
     * @since 25/09/2020
     * 
     * @return <code>Bandwidth</code> object
     */
    public Bandwidth getLimit() 
    {
        return Bandwidth.classic(bucketCapacity, Refill.intervally(bucketCapacity, Duration.ofMinutes(20)));
    }
    
    /**
     * Method to get the bucket capacity.
     * 
     * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
     * @since 25/09/2020
     * 
     * @return int
     */
    public int getBucketCapacity() 
    {
        return bucketCapacity;
    }
    
    /**
     * Method to get the right plan by looking the apiKey prefix.
     * 
     * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
     * @since 25/09/2020
     * 
     * @param apiKey
     * @return <code>APIUsagePlansEnum</code>
     */
    public static APIUsageTiersEnum resolvePlanFromApiKey(String apiKey) 
    {        
    	if (apiKey == null || apiKey.isEmpty()) 
    	{
            return FREE;
        
        } 
    	else if (apiKey.startsWith("PX001-")) 
    	{
            return PROFESSIONAL;
            
        } 
    	else if (apiKey.startsWith("BX001-")) 
    	{
            return BASIC;
        }
    	
        return FREE;
    }
}

