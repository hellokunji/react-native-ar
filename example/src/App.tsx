import * as React from 'react';

import { StyleSheet, View, Text, Button } from 'react-native';
import { multiply, showToast, startWorkManager } from '@hellokunji/react-native-ar';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    multiply(3, 7).then(setResult);
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
      <Button
        title="Show toast"
        onPress={() => showToast('Okay i am visible')}
      />
      <Button
        title="Show notification"
        onPress={() => startWorkManager()}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
