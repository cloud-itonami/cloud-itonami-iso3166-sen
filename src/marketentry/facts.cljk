(ns marketentry.facts "Senegal market-entry catalog.")
(def catalog
  {"SEN" {:name "Senegal"
          :owner-authority "ARMP / e-procurement"
          :legal-basis "Code des marchés publics"
          :national-spec "e-procurement + RCCM/NINEA"
          :provenance "https://www.armp.sn/"
          :required-evidence ["RCCM/NINEA record" "e-procurement registration record" "RCCM extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / ARMP"
          :rep-legal-basis "Senegalese legal entity (RCCM) typically required for public awards"
          :rep-provenance "https://www.armp.sn/"
          :corporate-number-owner-authority "APIX / DGID"
          :corporate-number-legal-basis "RCCM / NINEA"
          :corporate-number-provenance "https://www.apix.sn/"}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
