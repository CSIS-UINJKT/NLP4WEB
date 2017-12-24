package de.unihamburg.informatik.nlp4web.tutorial.tut5.feature;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Creates a list file (Entity Class) from wikipedia categories
 *
 * @author mirco
 *
 */
public class WikipediaListBuilder {

	public final static String NO_CATEGORY_FOUND = "O";
	private final static Pattern PATTERN = Pattern.compile("[A-Za-z 0-9',]+(?=\"})");
	private final Map<String, Collection<String>> indicators = new TreeMap<>();
	private final Map<String, String> cache = new TreeMap<>();
	private final static String RESULT = "wikipedia.list";
	private int tagged = 0;
	public PrintWriter writer;
	public Map<String, String> wikiCache;

	public WikipediaListBuilder() {

		indicators.put("I-PER", Lists.newArrayList("people by", "people in", "people from", "living people", "births",
				"deaths", "by occupation", "surname", "given names", "biography stub", "human names"));
		indicators.put("I-ORG",
				Lists.newArrayList("companies", "teams", "organizations", "businesses", "media by", "political parties",
						"clubs", "advocacy groups", "unions", "corporations", "newspapers", "agencies", "colleges",
						"universities", "legislatures", "company stub", "team stub", "university stub", "club stub"));
		indicators.put("I-LOC", Lists.newArrayList("cities", "countries", "territories", "counties", "villages",
				"municipalities", "states", "republics", "regions", "settlements", "areas", "towns"));
		indicators.put("I-MISC", Lists.newArrayList("days", "months", "years", "centuries", "wars", "lists",
				"incidents", "events", "cars"));
	}

	/**
	 * Querys the wikipedia for each word in the list (training set).
	 *
	 * @param wordList
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public final void create(String wordList) throws IOException, InterruptedException {
		LocalTime start = LocalTime.now();
		Set<String> lines = Util.readLines(wordList);
		System.err.println("Words in list: " + lines.size());
		for (String word : lines) {
			String tag = getTag(word);
			cache.put(word, tag);

		}

		write(RESULT);
		long minutes = Duration.between(start, LocalTime.now()).toMinutes();
		System.err.println("Ran for: " + minutes);
		System.err.println("Tagged: " + tagged);

	}

	private void write(String fileName) throws IOException {
		List<String> res = cache.entrySet().stream().map(e -> e.getKey() + " " + e.getValue())
				.collect(Collectors.toList());
		FileUtils.writeLines(new File(fileName), res, System.lineSeparator(), true);
	}

	/**
	 * Decides based on the categories which tag is most suitable for the
	 * article
	 *
	 * @param categories
	 * @return
	 */
	public final String getTag(String word) {
		String article = word.toLowerCase();

		List<String> categories;
		if (wikiCache == null) {
			wikiCache = new HashMap<>();
			try (BufferedReader reader = new BufferedReader(
					new FileReader(new File("src/main/resources/wikipedia-temp.txt")));) {
				String line;

				while ((line = reader.readLine()) != null) {
					String[] split = line.split(";;");
					wikiCache.put(split[0].toLowerCase(), split[1]);
				}

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		if (wikiCache.containsKey(article)) {
			Gson gson = new Gson();
			categories = gson.fromJson(wikiCache.get(article), List.class);
		} else {
			categories = getCategories(article);
			try {
				Writer output;
				output = new BufferedWriter(new FileWriter("src/main/resources/wikipedia-temp.txt", true));
				String json = new Gson().toJson(categories);
				output.append(article + ";;" + json + "\r\n");
				output.close();
			} catch (IOException e) {

			}
		}
		String tag = NO_CATEGORY_FOUND;
		Iterator<Entry<String, Collection<String>>> iterator = indicators.entrySet().iterator();

		while (tag.equals(NO_CATEGORY_FOUND) && iterator.hasNext()) {
			Entry<String, Collection<String>> next = iterator.next();
			Collection<String> indicator = next.getValue();
			for (String ind : indicator) {
				for (String cat : categories) {
					if (cat.matches("\\.*" + ind + "\\.*")) {
						tag = next.getKey();

						tagged++;

					}
				}
			}
		}
		if (!NO_CATEGORY_FOUND.equals(tag)) {
			System.err.println(article + "-->" + tag);
		}
		return tag;

	}

	/**
	 * Extracts the categories from a wikipedia article
	 *
	 * @param word
	 *            the article name
	 * @return list with categories
	 */
	private final List<String> getCategories(String word) {

		List<String> categories = new ArrayList<>();
		try {
			JsonNode asJson;

			asJson = Unirest.get("https://en.wikipedia.org/w/api.php?action=query&titles="
					+ URLEncoder.encode(word, "utf-8") + "&prop=categories&format=json").asJson().getBody();

			JSONObject object = asJson.getObject();
			JSONObject query = object.getJSONObject("query");
			JSONObject pages = query.getJSONObject("pages");
			JSONObject top = pages.getJSONObject(JSONObject.getNames(pages)[0]);
			JSONArray cat = top.getJSONArray("categories");
			Matcher matcher = PATTERN.matcher(cat.toString());
			while (matcher.find()) {
				categories.add(matcher.group().trim().toLowerCase());
			}
		} catch (UnsupportedEncodingException | UnirestException e) {
			categories = new ArrayList<>();
			e.printStackTrace();
		} catch (Exception e) {
			categories = new ArrayList<>();
		}
		return categories;

	}

}
