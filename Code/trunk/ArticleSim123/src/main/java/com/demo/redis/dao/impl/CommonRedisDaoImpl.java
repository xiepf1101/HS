package com.demo.redis.dao.impl;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import com.demo.redis.dao.CommonRedisDao;

@Repository
public class CommonRedisDaoImpl implements CommonRedisDao {

    private final RedisTemplate<String, String> redisTemplate;
    /**
     * 日志记录
     */
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CommonRedisDaoImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 缓存value操作
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    @Override
    public boolean cacheValue(String k, String v, long time) {
        String key = k;
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(key, v);
            if (time > 0) {
            	//到期时间
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存value操作
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    @Override
    public boolean cacheValue(String k, String v) {
        return cacheValue(k, v, -1);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k key
     * @return boolean
     */
    @Override
    public boolean containsValueKey(String k) {
        return containsKey(k);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k key
     * @return boolean
     */
    @Override
    public boolean containsSetKey(String k) {
        return containsKey(k);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k key
     * @return boolean
     */
    @Override
    public boolean containsListKey(String k) {
        return containsKey(k);
    }

    @Override
    public boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            LOGGER.error("判断缓存存在失败key[" + key + ", Codeor[" + t + "]");
        }
        return false;
    }

    /**
     * 获取缓存
     *
     * @param k key
     * @return string
     */
    @Override
    public String getValue(String k) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            return valueOps.get(k);
        } catch (Throwable t) {
            LOGGER.error("获取缓存失败key[" + k + ", Codeor[" + t + "]");
        }
        return null;
    }

    /**
     * 移除缓存
     *
     * @param k key
     * @return boolean
     */
    @Override
    public boolean removeValue(String k) {
        return remove(k);
    }

    @Override
    public boolean removeSet(String k) {
        return remove(k);
    }

    @Override
    public boolean removeList(String k) {
        return remove(k);
    }


    /**
     * 缓存set操作
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    @Override
    public boolean cacheSet(String k, String v, long time) {
        String key = k;
        try {
            SetOperations<String, String> valueOps = redisTemplate.opsForSet();
            valueOps.add(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    @Override
    public boolean cacheSet(String k, String v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 缓存set
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    @Override
    public boolean cacheSet(String k, Set<String> v, long time) {
        String key = k;
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            setOps.add(key, v.toArray(new String[v.size()]));
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    @Override
    public boolean cacheSet(String k, Set<String> v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 获取缓存set数据
     *
     * @param k key
     * @return set
     */
    @Override
    public Set<String> getSet(String k) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            return setOps.members(k);
        } catch (Throwable t) {
            LOGGER.error("获取set缓存失败key[" + k + ", Codeor[" + t + "]");
        }
        return null;
    }

    /**
     * list缓存
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    @Override
    public boolean cacheList(String k, String v, long time) {
        String key = k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPush(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    @Override
    public boolean cacheList(String k, String v) {
        return cacheList(k, v, -1);
    }

    /**
     * 缓存list
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    @Override
    public boolean cacheList(String k, List<String> v, long time) {
        String key = k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPushAll(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    @Override
    public boolean cacheList(String k, List<String> v) {
        return cacheList(k, v, -1);
    }

    /**
     * 获取list缓存
     *
     * @param k     key
     * @param start start
     * @param end   end
     * @return list
     */
    @Override
    public List<String> getList(String k, long start, long end) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.range(k, start, end);
        } catch (Throwable t) {
            LOGGER.error("获取list缓存失败key[" + k + ", Codeor[" + t + "]");
        }
        return null;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param k key
     * @return long
     */
    @Override
    public long getListSize(String k) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.size(k);
        } catch (Throwable t) {
            LOGGER.error("获取list长度失败key[" + k + "], Codeor[" + t + "]");
        }
        return 0;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param listOps listOps
     * @param k       k
     * @return long
     */
    @Override
    public long getListSize(ListOperations<String, String> listOps, String k) {
        try {
            return listOps.size(k);
        } catch (Throwable t) {
            LOGGER.error("获取list长度失败key[" + k + "], Codeor[" + t + "]");
        }
        return 0;
    }

    /**
     * 移除list缓存
     *
     * @param k k
     * @return boolean
     */
    @Override
    public boolean removeOneOfList(String k) {
        String key = k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPop(key);
            return true;
        } catch (Throwable t) {
            LOGGER.error("移除list缓存失败key[" + k + ", Codeor[" + t + "]");
        }
        return false;
    }

    /**
     * 移除缓存
     *
     * @param key key
     * @return boolean
     */
    private boolean remove(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Throwable t) {
            LOGGER.error("获取缓存失败key[" + key + ", Codeor[" + t + "]");
        }
        return false;
    }
    
    public DefaultStringRedisConnection select(Integer dbId){
    	RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
    	DefaultStringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
    	stringRedisConnection.select(dbId);
    	return stringRedisConnection;
    }

	@Override
	public List<String> selectToFind(Integer dbId, String key) {
		// TODO Auto-generated method stub
		RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
    	DefaultStringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
    	stringRedisConnection.select(dbId);
    	List<String> list = stringRedisConnection.lRange(key, 0, -1);
    	if(list.isEmpty()){
    		return null;
    	}
		return list;
	}

	@Override
	public boolean selectToSave(Integer dbId, String key, String value) {
		// TODO Auto-generated method stub
		RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
    	DefaultStringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
    	stringRedisConnection.select(dbId);
    	stringRedisConnection.lPush(key, value);
		return true;
	}

	@Override
	public boolean saveSimHashFragment(String simHash) {
		// TODO Auto-generated method stub
		RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
    	DefaultStringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(redisConnection);
		String simHash1 = simHash.substring(0,16);
		String simHash2 = simHash.substring(16, 32);
		String simHash3 = simHash.substring(32, 48);
		String simHash4 = simHash.substring(48);
		stringRedisConnection.select(1);
		stringRedisConnection.lPush(simHash1, simHash);
		stringRedisConnection.select(2);
		stringRedisConnection.lPush(simHash2, simHash);
		stringRedisConnection.select(3);
		stringRedisConnection.lPush(simHash3, simHash);
		stringRedisConnection.select(4);
		stringRedisConnection.lPush(simHash4, simHash);
		return true;
	}
    
}
