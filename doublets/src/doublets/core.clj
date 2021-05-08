;;;;
;;;;
;;;;   In Clojure, because the language is so bendable, you actually bend language towards the problem, not the problem towards the language.
;;;;   -- Neal Ford
;;;;
;;;;   Name:               doublets.clj
;;;;
;;;;   Started:            Sat May  1 20:39:09 2021
;;;;   Modifications:
;;;;
;;;;   Purpose:
;;;;
;;;;
;;;;
;;;;   Calling Sequence:
;;;;
;;;;
;;;;   Inputs:
;;;;
;;;;   Outputs:
;;;;
;;;;   Example:
;;;;
;;;;   Notes: This solution passes the book's tests, but it falls over when applied to a more substantial dictionary. Even limiting the dictionary
;;;;   to same-length words and memoizing the index, these algorithms are not adequate for thousands of candidates.
;;;;
;;;;    For example, using the medium sized dictionary from Slade ch. 6 (~45,000 words), this is one result (This one actually succeeds):
;; doublets.core=> (doublets "wheat" "bread")
;; ["wheat" "cheat" "chest" "crest" "crust" "crush" "brush" "brash" "trash" "crash" "clash" "class" "clams" "claps" "flaps" "flips" "blips" "clips" "chips" "chins" "chink" "chunk" "chuck" "check" "cheek" "cheer" "sheer" "sheep" "sleep" "sleet" "sweet" "sweat" "swear" "smear" "shear" "sheaf" "shelf" "shell" "spell" "spill" "shill" "skill" "skull" "skulk" "skunk" "spunk" "spank" "swank" "swans" "spans" "scans" "scars" "scare" "scarf" "scary" "scaly" "scale" "stale" "stile" "smile" "smite" "suite" "spite" "spice" "space" "spare" "spark" "shark" "sharp" "shard" "share" "shape" "shake" "stake" "stoke" "stove" "stave" "slave" "suave" "shave" "shame" "shams" "seams" "teams" "beams" "beans" "means" "meals" "deals" "reals" "reels" "feels" "heels" "heeds" "weeds" "deeds" "deems" "teems" "seems" "seers" "seeks" "seeps" "steps" "stops" "shops" "chops" "coops" "hoops" "loops" "looks" "books" "boors" "Coors" "doors" "dooms" "zooms" "booms" "boots" "booth" "tooth" "sooth" "sloth" "slots" "slits" "slats" "slays" "plays" "clays" "claws" "flaws" "flags" "flats" "feats" "felts" "belts" "beats" "Keats" "meats" "moats" "goats" "goals" "coals" "cools" "tools" "toils" "tails" "wails" "sails" "jails" "fails" "hails" "hairs" "lairs" "fairs" "fairy" "dairy" "hairy" "harry" "Larry" "Garry" "parry" "Darry" "marry" "merry" "Perry" "ferry" "furry" "hurry" "curry" "curly" "burly" "bully" "fully" "filly" "Willy" "silly" "silky" "milky" "milks" "silks" "sulks" "bulks" "balks" "walks" "wales" "vales" "vases" "eases" "bases" "baser" "laser" "lager" "Hager" "eager" "eater" "water" "wafer" "waxer" "waxen" "waxes" "wages" "wanes" "lanes" "lands" "bands" "bonds" "binds" "birds" "bards" "yards" "wards" "warps" "warns" "wares" "Lares" "pares" "parts" "darts" "carts" "marts" "mares" "mates" "sates" "Gates" "Yates" "hates" "hazes" "gazes" "gazer" "gazed" "gaped" "taped" "taxed" "taxes" "tapes" "rapes" "rakes" "fakes" "fames" "tames" "games" "James" "Jakes" "takes" "taker" "baker" "baked" "waked" "waved" "saved" "saves" "caves" "paves" "pages" "paces" "panes" "pangs" "fangs" "hangs" "gangs" "gongs" "longs" "tongs" "tones" "zones" "zoned" "toned" "tuned" "tunes" "tubes" "cubes" "cubed" "cured" "lured" "lures" "cures" "curbs" "curls" "culls" "hulls" "hills" "Mills" "bills" "fills" "pills" "polls" "poles" "pokes" "yokes" "jokes" "joker" "joked" "poked" "posed" "poser" "Moser" "Moyer" "Mayer" "payer" "paper" "raper" "rarer" "barer" "borer" "boxer" "boxes" "bodes" "codes" "comes" "domes" "dopes" "doper" "doped" "domed" "homed" "holed" "holes" "holds" "colds" "cords" "corns" "corps" "cores" "corer" "cover" "lover" "loves" "lobes" "robes" "robed" "roped" "coped" "cored" "cowed" "vowed" "bowed" "bower" "boner" "loner" "honer" "honey" "hones" "hoses" "hosts" "posts" "pouts" "pours" "tours" "yours" "hours" "sours" "soars" "soaks" "soaps" "swaps" "snaps" "slaps" "slops" "slope" "scope" "score" "scorn" "shorn" "short" "shout" "snout" "spout" "sport" "spurt" "spurn" "spurs" "slurs" "slums" "slump" "plump" "clump" "clamp" "cramp" "crams" "crabs" "grabs" "grams" "grass" "glass" "gloss" "glows" "blows" "blown" "clown" "crown" "grown" "groin" "grain" "drain" "train" "trail" "trait" "tract" "track" "crack" "crank" "crane" "craze" "crate" "irate" "prate" "plate" "place" "peace" "peach" "beach" "beech" "bench" "bunch" "punch" "lunch" "munch" "hunch" "hutch" "hatch" "latch" "batch" "catch" "watch" "witch" "ditch" "Fitch" "Mitch" "bitch" "hitch" "pitch" "patch" "parch" "perch" "porch" "pooch" "poach" "coach" "couch" "touch" "tough" "dough" "bough" "rough" "rouge" "gouge" "gorge" "Jorge" "forge" "forte" "forts" "fords" "forms" "worms" "words" "wordy" "woody" "goody" "goofy" "goofs" "roofs" "roots" "hoots" "hoods" "moods" "moons" "moans" "loans" "loads" "toads" "roads" "roams" "roars" "rears" "Sears" "fears" "nears" "gears" "hears" "heaps" "heals" "heats" "seats" "seals" "peals" "peaks" "pecks" "necks" "nicks" "kicks" "wicks" "winks" "pinks" "minks" "mines" "miner" "diner" "diver" "river" "giver" "gives" "wives" "waves" "haves" "eaves" "raves" "raven" "raved" "raged" "rated" "fated" "dated" "dazed" "dared" "dares" "cares" "cared" "caved" "paved" "payed" "paced" "faced" "faked" "raked" "raped" "raced" "racer" "pacer" "pager" "cager" "cages" "cafes" "safes" "safer" "saner" "saver" "sever" "lever" "never" "fever" "fewer" "hewer" "hewed" "mewed" "meted" "meter" "deter" "dater" "mater" "maker" "faker" "fader" "faded" "jaded" "waded" "waxed" "waged" "caged" "caked" "cakes" "bakes" "bikes" "hikes" "hires" "hired" "tired" "mired" "mires" "mixes" "sixes" "sines" "sides" "sites" "rites" "rides" "Aides" "hides" "tides" "tires" "times" "timed" "tiled" "filed" "filer" "fixer" "firer" "airer" "aimer" "armer" "armed" "aimed" "aided" "tided" "sided" "sized" "sired" "siren" "sires" "sores" "mores" "moves" "doves" "dover" "rover" "roves" "ropes" "hopes" "copes" "coves" "covet" "comet" "comer" "homer" "hover" "hovel" "novel" "navel" "naval" "natal" "fatal" "fetal" "petal" "pedal" "medal" "modal" "model" "modem" "modes" "nodes" "noses" "nosed" "dosed" "dozed" "dozen" "dozes" "doles" "dales" "dates" "Bates" "Bayes" "Hayes" "Hades" "wades" "wader" "wider" "wiper" "wiped" "wined" "lined" "lines" "vines" "Hines" "nines" "pines" "pikes" "piker" "poker" "power" "vower" "voter" "votes" "dotes" "notes" "noted" "doted" "doled" "poled" "pored" "bored" "boxed" "boned" "bones" "bores" "pores" "poses" "roses" "rises" "risks" "disks" "desks" "decks" "ducks" "tucks" "bucks" "bunks" "bunts" "hunts" "hints" "hilts" "tilts" "wilts" "silts" "sills" "Wills" "gills" "galls" "calls" "calms" "balms" "palms" "pales" "bales" "babes" "bares" "barbs" "barns" "burns" "burps" "bumps" "pumps" "lumps" "limps" "limbs" "lambs" "lames" "laces" "maces" "makes" "manes" "mazes" "males" "mules" "muses" "buses" "bused" "fused" "fumed" "famed" "lamed" "lazed" "laced" "maced" "mated" "sated" "hated" "gated" "gamed" "named" "namer" "tamer" "taper" "toper" "roper" "rower" "cower" "coder" "cider" "rider" "riser" "wiser" "miser" "miter" "biter" "liter" "liver" "lives" "fives" "files" "Miles" "tiles" "tills" "tells" "bells" "belly" "Kelly" "jelly" "jolly" "Solly" "Molly" "golly" "folly" "dolly" "dully" "gully" "gulls" "nulls" "lulls" "pulls" "dulls" "dells" "hells" "halls" "halts" "salts" "malts" "masts" "masks" "tasks" "tanks" "ranks" "racks" "hacks" "sacks" "packs" "picks" "Hicks" "ticks" "licks" "links" "sinks" "sings" "kings" "kinds" "winds" "finds" "fines" "wines" "wings" "rings" "rinds" "rends" "pends" "bends" "mends" "menus" "minus" "mints" "pints" "pants" "pasts" "paste" "caste" "casts" "lasts" "lists" "fists" "mists" "misty" "musty" "musts" "rusts" "rests" "nests" "vests" "pests" "pelts" "melts" "meets" "beets" "beefs" "reefs" "reeds" "seeds" "sends" "sands" "hands" "handy" "dandy" "randy" "sandy" "bandy" "candy" "canny" "Danny" "Denny" "Lenny" "jenny" "penny" "peony" "phony" "phone" "prone" "prose" "probe" "prove" "drove" "grove" "glove" "clove" "close" "chose" "chase" "cease" "tease" "lease" "least" "feast" "beast" "boast" "blast" "blase" "blare" "flare" "flake" "flame" "blame" "blaze" "blade" "glade" "grade" "grape" "gripe" "grime" "crime" "clime" "slime" "slide" "slice" "slick" "slack" "flack" "flick" "click" "clock" "block" "blocs" "blobs" "blots" "plots" "plows" "prows" "prowl" "growl" "grows" "gross" "cross" "crops" "drops" "drips" "trips" "trims" "tries" "dries" "dried" "cried" "creed" "breed" "bread"]
;; doublets.core=> (count *1)
;; 928
;;;;

(ns doublets.core
  (:use clojure.test
        clojure.set
        [clojure.pprint :only (cl-format)])
  (:import))

;; (doublets "love" "hate")
;; (doublets "lust" "love")

(def dictionary (load-file "src/doublets/words.edn"))
;(def dictionary (load-file "src/doublets/words.big.edn"))
;  (with-open [r (clojure.java.io/reader "/home/slytobias/lisp/books/Slade/ch06/wordlists/words")] (.readLine r))

(def ^:constant marker \_)

;; (defn index-word
;;   "Index the given word for convenient analysis."
;;   [word]
;;   (let [v (vec word)]
;;     (into #{} (map #(assoc v % \_) (range (count word)))) ))
(def index-word (memoize (fn [word]
                           (let [v (vec word)]
                             (into #{} (map #(assoc v % marker) (range (count word)))) ))))

(defn single-degree? [indexed-word indexed-candidate]
  (seq (intersection indexed-word indexed-candidate)))

;;;
;;;    `word` is in dictionary?
;;;    
(defn find-single-degree
  ([word] (find-single-degree word (filter #(== (count %) (count word)) dictionary)))
  ([word dict]
   (let [size (count word)
         indexed (index-word word)]
;    (filter #(and (not= % word) (== (count %) size) (single-degree word %)) dictionary))
;     (filter (every-pred #(not= % word) #(== (count %) size) #(single-degree? indexed (index-word %))) dict))))
     (filter (every-pred #(not= % word) #(single-degree? indexed (index-word %))) dict))))

(defn doublets
  ([start finish] (doublets start finish [] (filter #(== (count %) (count start)) dictionary)))
  ([start finish path dict]
;   (cl-format true "~A~%" start)
   (if (= start finish)
    (conj path finish)
    (let [candidates (difference (set (find-single-degree start dict)) (set path))]
      (if (empty? candidates)
        (do ;(cl-format true "Outta luck!~%")
            [])
        (if (= (count candidates) 1)
          (doublets (first candidates) finish (conj path start) dict)
          (some (fn [candidate] 
                  (let [result (doublets candidate finish (conj path start) dict)]
                    (if (empty? result)
                      false
                      result)))
                candidates)))) )))

(deftest test-doublets
  (testing "Expected success"
    (is (= (doublets "head" "tail") ["head" "heal" "teal" "tell" "tall" "tail"]))
    (is (= (doublets "door" "lock") ["door" "boor" "book" "look" "lock"]))
    (is (= (doublets "bank" "loan") ["bank" "bonk" "book" "look" "loon" "loan"]))
    (is (= (doublets "wheat" "bread") ["wheat" "cheat" "cheap" "cheep" "creep" "creed" "breed" "bread"])))
  (testing "Expected failure"
    (is (empty? (doublets "ye" "freezer"))))
  (testing "Reverse paths" ; This is not reasonable to assume that both paths are identical...
    (is (= (doublets "head" "tail") (reverse (doublets "tail" "head"))))
    (is (= (doublets "door" "lock") (reverse (doublets "lock" "door"))))
    (is (= (doublets "bank" "loan") (reverse (doublets "loan" "bank"))))
    (is (= (doublets "wheat" "bread") (reverse (doublets "bread" "wheat")))) ))

