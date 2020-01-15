(ns clojure-assignment.core)

(def locations ["Dispensary" "ICU" "Warehouse"])
(def medical-items ["Paracetamol" "Ibuprofen" "Syringe" "Scalpel"])
(def quantities [[10 50 80 100] [0 20 7 8] [50 33 10 80]])
(def transactions
  [{"from" "Warehouse" "to" "Dispensary" "item" "Paracetamol" "quantity" 2}
   {"from" "Warehouse" "to" "ICU" "item" "Paracetamol" "quantity" 5}
   {"from" "ICU" "to" "Dispensary" "item" "Scalpel" "quantity" 8}])

(defn map-values [locations med quantity]
  (zipmap locations (map (partial zipmap med) quantity)))

(defn apply-operator [operator first-value second-value] (operator first-value second-value))
(defn get-keys [transaction reference-key] [(transaction reference-key) (transaction "item")])

(defn apply-transaction [initial-values {from "from" to "to" item "item" quantity "quantity"}]
  (update-in (update-in initial-values [from item] - quantity) [to item] + quantity))

(defn apply-transactions [initial-values transactions]
  (reduce apply-transaction initial-values transactions))

(apply-transactions (map-values locations medical-items quantities) transactions)