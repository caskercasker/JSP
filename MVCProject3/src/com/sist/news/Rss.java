package com.sist.news;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/*
 * 	JAXP(parsing) => Java Api For XML Parse
 * 					 설정 파일 읽기 (Mybatis,Spring...)
 * 					 = DOM (Document Object Model) 	=> 메모리에 저장  (수정,삭제,추가)
 * 					 = SAX (Simple API for XML)     => 읽기 전용 (스프링,마이바티스)		SAX parsing 
 * 
 * 	JAXB(binding)  => Java Api For XML Bind => Annotation (빅데이터용)
 * 		=> marshal 마셜  ===> Java class에 있는 데이터 => XML 변환
 * 		=> unmarshal 언마셜 ==> XML = >Java Object
 *
 *	<rss> 클래스
 *		<channel>클래스
 *			<item> 클래스
 *				<title>변수</title>
 *				<author></author>
 *				<description></description>
 *				<link></link>			
 *			</item>
 *			<item>
 *				<title></title>
 *				<author></author>
 *				<description></description>
 *				<link></link>			
 *			</item>
 *			<item>
 *				<title></title>
 *				<author></author>
 *				<description></description>
 *				<link></link>			
 *			</item>
 *		</channel>
 	</rss>
 */

@XmlRootElement
public class Rss {
	private Channel channel = new Channel();

	public Channel getChannel() {
		return channel;
	}
	
	@XmlElement
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
