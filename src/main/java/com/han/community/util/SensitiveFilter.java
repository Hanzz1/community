package com.han.community.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 */
@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    //替换符号
    private static final String REPLACEMENT = "***";

    //根节点初始化
    private TrieNode rootNode = new TrieNode();

    //初始化注解
    @PostConstruct
    public void init() {
        try (
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ) {
            String keyword;
            while ((keyword = reader.readLine()) != null) {
                //添加到前缀
                this.addKeyword(keyword);
            }
        } catch (IOException e) {
            logger.error("敏感词" + e.getMessage());
        }
    }

    //将一个敏感词加入到词缀树中
    private void addKeyword(String keyword) {
        TrieNode tempNode = rootNode;
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);
            if (subNode == null) {
                subNode = new TrieNode();
                tempNode.addSubNode(c, subNode);
            }
            /*指向子节点 进入下次循环*/
            tempNode = subNode;
            /*设置结束表示*/
            if (i == keyword.length() - 1) tempNode.setKeywordEnd(true);
        }
    }

    /**
     * 过滤敏感词
     *
     * @param text 待过滤的文本
     * @return 过滤后的文本
     */
    public String filter(String text) {

        if (StringUtils.isBlank(text)) {
            return null;
        }
        //指针一 指向树
        TrieNode tempNode = rootNode;
        //指针二
        int begin = 0;
        //指针三
        int position = 0;
        //结果
        StringBuilder sb = new StringBuilder();
        while (position < text.length()) {
            char c = text.charAt(position);
            //  跳过符号
            if (isSymbool(c)) {
                //若指针1处于根节点，将符号计入结果，让指针2向下走一步
                if (tempNode == rootNode) {
                    sb.append(c);
                    begin++;
                }
                //无论符号在开头或者中间，指针都向下走一步
                position++;
                continue;
            }
            tempNode = tempNode.getSubNode(c);
            if (tempNode == null) {
                //以begin开头的字符串不是敏感词
                sb.append(text.charAt(begin));
                position = ++begin;
                tempNode = rootNode;
            } else if (tempNode.isKeywordEnd) {
                //发现敏感词将begin--position字符串替换
                sb.append(REPLACEMENT);
                begin = ++position;
                tempNode = rootNode;
            } else {
                position++;
            }

        }
        sb.append(text.substring(begin));
        return sb.toString();
    }

    //判断是否为符号
    private boolean isSymbool(Character c) {
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    /*前缀树*/
    private class TrieNode {
        //关键词结束标识
        private boolean isKeywordEnd = false;

        //子节点 key是下级字符 value下级节点
        private Map<Character, TrieNode> subNode = null;

        public boolean isKeywordEnd() {
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

        //添加子节点
        public void addSubNode(Character c, TrieNode node) {
            subNode.put(c, node);
        }

        //获取子节点
        public TrieNode getSubNode(Character c) {
            return subNode.get(c);
        }
    }

}
