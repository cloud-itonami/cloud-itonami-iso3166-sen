(ns culture.facts
  "Country-level regional-culture catalog for Senegal (SEN) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"SEN"
   [{:culture/id "sen.dish.thieboudienne"
     :culture/name "Thieboudienne"
     :culture/name-local "Ceebu jën"
     :culture/country "SEN"
     :culture/kind :dish
     :culture/summary "One-pot dish of fish, broken rice and tomato sauce; the article states it is the national dish of Senegal, originating in Saint-Louis, and UNESCO has recognized Ceebu Jen as an intangible cultural heritage."
     :culture/url "https://en.wikipedia.org/wiki/Thieboudienne"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sen.dish.yassa"
     :culture/name "Yassa"
     :culture/country "SEN"
     :culture/kind :dish
     :culture/summary "Spicy dish of marinated poultry, fish or lamb with onions and lemon or mustard; the article states it is originally from Senegal and one of the most iconic dishes of Senegalese cuisine, since spread throughout West Africa."
     :culture/url "https://en.wikipedia.org/wiki/Yassa_(food)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sen.dish.mafe"
     :culture/name "Peanut stew"
     :culture/name-local "Maafe"
     :culture/country "SEN"
     :culture/kind :dish
     :culture/summary "Staple West African groundnut stew (Wolof mafe); the article states the dish originates from Senegal, though a stew of similar name (tigadeguena) also originated in Mali, and maafe subsequently spread across the region."
     :culture/url "https://en.wikipedia.org/wiki/Peanut_stew"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sen.beverage.cafe-touba"
     :culture/name "Cafe Touba"
     :culture/name-local "Café Touba"
     :culture/country "SEN"
     :culture/kind :beverage
     :culture/summary "Coffee flavored with grains of Selim (locally djar) and named for the city of Touba; traditionally consumed by the Mouride Islamic brotherhood since its founder returned from exile in 1902, and served during ceremonies and the Grand Magal of Touba."
     :culture/url "https://en.wikipedia.org/wiki/Caf%C3%A9_Touba"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sen.craft.senegalese-kaftan"
     :culture/name "Senegalese kaftan"
     :culture/name-local "Mbubb"
     :culture/country "SEN"
     :culture/kind :craft
     :culture/summary "Pullover men's robe with long bell sleeves, ankle-length, commonly made of cotton brocade or lace and paired with matching tubay pants; called mbubb or xaftaan in Wolof and boubou in French, worn as formal wear across West Africa."
     :culture/url "https://en.wikipedia.org/wiki/Senegalese_kaftan"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sen.festival.senegalese-wrestling"
     :culture/name "Senegalese wrestling"
     :culture/name-local "Laamb"
     :culture/country "SEN"
     :culture/kind :festival
     :culture/summary "Folk wrestling traditionally performed by the Serer people, called Laamb in Wolof; holds the status of national sport in Senegal, with matches including ceremonial performances and the bakk oral boasting art, and has become a major spectator event since the 1950s."
     :culture/url "https://en.wikipedia.org/wiki/Senegalese_wrestling"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "sen.heritage.goree"
     :culture/name "Island of Goree"
     :culture/name-local "Île de Gorée"
     :culture/country "SEN"
     :culture/kind :heritage
     :culture/summary "Small island off the coast of Dakar, designated a UNESCO World Heritage Site in 1978 as one of the first 12 locations so designated worldwide; a memorial to the transatlantic slave trade and Senegal's premier tourist site."
     :culture/url "https://en.wikipedia.org/wiki/Gor%C3%A9e"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-sen culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "SEN"))
                 " SEN entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
