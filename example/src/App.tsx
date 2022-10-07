import * as React from 'react';

import { StyleSheet, View, Text, Button } from 'react-native';
import {
  startARActivity,
  startTrip,
  endTrip,
} from '@hellokunji/react-native-ar';

export default function App() {
  // const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    // multiply(3, 7).then(setResult);
  }, []);

  return (
    <View style={styles.container}>
      <Text>REACT NATIVE SCREEN</Text>
      <View style={styles.ar}>
        <Text>Activity Recognition</Text>
        <View style={styles.btn}>
          <Button title="Trip Start" onPress={() => startTrip()} />
          <Button title="Trip End" onPress={() => endTrip()} />
        </View>
      </View>
      <Button title="Start dummy activity" onPress={() => startARActivity()} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingHorizontal: 10,
  },
  ar: {
    backgroundColor: 'lightgrey',
    marginTop: 50,
    marginBottom: 50,
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'space-evenly',
    alignItems: 'center',
    paddingVertical: 30,
    borderRadius: 20,
  },
  btn: {
    width: '100%',
    marginTop: 30,
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-evenly',
    alignItems: 'center',
  },
});
